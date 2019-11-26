package com.hms.provider.service.impl;

import com.hms.RadomUtil;
import com.hms.base.constant.GlobalConstant;
import com.hms.core.support.BaseService;
import com.hms.provider.dao.OrderDao;
import com.hms.provider.model.domain.OrderInfo;
import com.hms.provider.model.dto.OrderDto;
import com.hms.provider.model.dto.OrderFrontDto;
import com.hms.provider.model.vo.OrderVo;
import com.hms.provider.service.OmsOrderService;
import com.hms.provider.service.RedisService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hms.base.constant.GlobalConstant.Order.ORDER_HASH_KEY;
import static com.hms.base.constant.GlobalConstant.Order.ORDER_ID;
import static com.hms.base.constant.MqConstant.DELAY_ORDER_EXCHANGE;
import static com.hms.base.constant.MqConstant.DELAY_ORDER_ROUTING_KEY;

/**
 * @author luoshao
 * @date 2019/6/1 23:12
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class OmsOrderServiceImpl extends BaseService implements OmsOrderService {

    //动态代理
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    RedisService redisService;

//    @Autowired
//    OmsFeignApi omsFeignApi;

//    @Autowired
//    RmsFeignApi rmsFeignApi;

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderVo[] queryOrderInfo(String userid){

        List<OrderInfo> orderinfos = orderDao.queryFromOrderInfo(userid);
        OrderVo[] orderVos = new ModelMapper().map(orderinfos, OrderVo[].class);
        return orderVos;
    }

    @Override
    @Transactional
    public String createOrderInfo(OrderDto orderdto){


        int stock = (int)redisService.getKey(GlobalConstant.Room.REDIS_ROOM_STOCK + orderdto.getRoom_id());
        logger.info("剩余库存数为:{}",stock);
        //Preconditions.checkArgument(!(stock < 0),"库存为0");
        if(stock <= 0){
            return "";
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setHotel_name(orderdto.getHotel_name());
        orderInfo.setHotel_id(orderdto.getHotel_id());
        orderInfo.setUser_id(orderdto.getUser_id());
        orderInfo.setOrder_set_time(new Date(System.currentTimeMillis()));
        orderInfo.setRoom_in_time(orderdto.getRoom_in_time());
        orderInfo.setRoom_out_time(orderdto.getRoom_out_time());
        orderInfo.setRoom_name(orderdto.getRoom_name());
        orderInfo.setPay_status(0);
        orderInfo.setRoom_count(orderdto.getRoom_count());
        orderInfo.setRoom_id(orderdto.getRoom_id());
        orderInfo.setCollection_price(orderdto.getCollection_price());
        //判断是否存在有重复的订单
        if(redisService.hasKey(ORDER_HASH_KEY + orderInfo.getRoom_id() + orderInfo.getUser_id())){
            String order_id = (String) redisService.getKey(ORDER_HASH_KEY + orderInfo.getRoom_id() + orderInfo.getUser_id());
            //如果存在则更新重复订单的时间，更新ORDER_ID和ORDER_ID
            redisService.setKey(ORDER_HASH_KEY + orderInfo.getRoom_id() + orderInfo.getUser_id(), order_id,90, TimeUnit.SECONDS);
            redisService.setKey(ORDER_ID + order_id , orderInfo.getPay_status(),90, TimeUnit.SECONDS);
            orderInfo.setOrder_id(order_id);
        }else {
            //如果不存在则创建一个新的订单
            orderInfo.setOrder_id(RadomUtil.createRadomOrderID() + "");
            redisService.setKey(ORDER_HASH_KEY + orderInfo.getRoom_id() + orderInfo.getUser_id(),orderInfo.getOrder_id(),90,TimeUnit.SECONDS);
            redisService.setKey(ORDER_ID + orderInfo.getOrder_id(), orderInfo.getPay_status(),90, TimeUnit.SECONDS);
            orderDao.insertOrderInfo(orderInfo);
            redisService.decr(GlobalConstant.Room.REDIS_ROOM_STOCK + orderdto.getRoom_id());
        }
        logger.info("发送订单信息给延时队列");
        rabbitTemplate.convertAndSend(DELAY_ORDER_EXCHANGE,DELAY_ORDER_ROUTING_KEY,SerializationUtils.serialize(orderInfo),message -> {
            message.getMessageProperties().setExpiration(60 * 1000 + "");
            return message;
        });
        return orderInfo.getOrder_id();
    }

    @Transactional
    public boolean commitOrder(int order_id,int room_id,String room_name,String mobile){
        logger.info("更新订单状态:{}",order_id);
        //还需要删除ORDER_HASH_KEY
        redisService.deleteKey(ORDER_HASH_KEY + room_id + mobile);
        redisService.deleteKey(ORDER_ID + order_id);
        orderDao.updateOrderInfo(1,order_id);
        OrderInfo orderInfo = orderDao.queryByOrderID(order_id + "");
        OrderFrontDto orderFrontDto = new OrderFrontDto();
//        orderFrontDto.setRoomId((int)rmsFeignApi.getRandomRoom(room_name).getResult()); //随机获取客房
        orderFrontDto.setUserId(orderInfo.getUser_id());
        orderFrontDto.setCheckIn(orderInfo.getRoom_in_time());
        orderFrontDto.setCheckOut(orderInfo.getRoom_out_time());
        orderFrontDto.setCheckInStatus(1);
        orderFrontDto.setOrigin("妹团订购单");
        orderFrontDto.setUsername(mobile +"");
        orderFrontDto.setPhone(mobile + "");
//        boolean is_success = (boolean)omsFeignApi.commitLocalOrder(orderFrontDto).getResult();
//        Preconditions.checkArgument(is_success,"向酒店提交订单失败");
        return true;
    }

}
