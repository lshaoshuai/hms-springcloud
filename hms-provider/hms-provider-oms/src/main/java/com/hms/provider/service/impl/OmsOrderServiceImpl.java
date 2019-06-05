package com.hms.provider.service.impl;

import com.google.common.base.Preconditions;
import com.hms.base.constant.GlobalConstant;
import com.hms.core.support.BaseService;
import com.hms.provider.dto.OrderDto;
import com.hms.provider.service.OmsOrderService;
import com.hms.provider.service.RedisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.hms.base.constant.MqConstant.*;

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

    @Override
    public OrderDto queryOrderInfo(){
        OrderDto orderDto = new OrderDto();
        return orderDto;
    }

    @Override
    public void sendOrderInfoToMq(int roomid, long mobile){
        logger.info("房间号为:{},手机号为:{}",roomid, mobile);
        long stock = redisService.decr(GlobalConstant.Room.REDIS_ROOM_STOCK + roomid);
        logger.info("剩余库存数为:{}",stock);
        Preconditions.checkArgument(!(stock < 0),"库存为0");
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_ROUTING_KEY, stock, new CorrelationData(String.valueOf(mobile)));
    }
}
