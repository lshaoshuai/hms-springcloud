package com.hms.provider.mq.consumer;

import com.hms.base.constant.GlobalConstant;
import com.hms.provider.dao.OrderDao;
import com.hms.provider.domain.OrderInfo;
import com.hms.provider.service.RedisService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

import static com.hms.base.constant.GlobalConstant.Order.ORDER_HASH_KEY;
import static com.hms.base.constant.GlobalConstant.Order.ORDER_ID;
import static com.hms.base.constant.MqConstant.ORDER_QUEUE;

/**
 * @author luoshao
 * @date 2019/6/8 14:36
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Slf4j
@Component
public class DelayOrderConsumer {
    /**
     * queues  指定从哪个队列（queue）订阅消息
     * @param message
     * @param channel
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OrderDao orderDao;

    @Resource
    RedisService redisService;

    @RabbitListener(queues = {ORDER_QUEUE})
    public void handleMessage(Message message, Channel channel) throws IOException {
        try {

            logger.info("OrderConsumer {} handleMessage :"+ message.getBody());
            OrderInfo orderInfo = (OrderInfo) SerializationUtils.deserialize(message.getBody());
            logger.info("orderInfo {}:"+ orderInfo);
            if (!redisService.hasKey(ORDER_ID + orderInfo.getOrder_id())){
                //拒绝确认 deliveryTag:该消息的index  boolean requeue：是否重新入队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            }

            int pay_status = (int)redisService.getKey(ORDER_ID + orderInfo.getOrder_id());

            // 处理消息
            if(pay_status == 0){
//                redisService.setKey(ORDER_ID + orderInfo.getOrder_id(),-1,0, TimeUnit.SECONDS);
                redisService.deleteKey(ORDER_ID + orderInfo.getOrder_id());
                redisService.deleteKey(ORDER_HASH_KEY + orderInfo.getRoom_id() + orderInfo.getUser_id());
                redisService.incr(GlobalConstant.Room.REDIS_ROOM_STOCK + orderInfo.getRoom_id());
                orderDao.updateOrderInfo(-1,Integer.parseInt(orderInfo.getOrder_id()));
            }

        }catch (Exception e){
            logger.error("超时订单处理失败:{}", e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        }
    }
}
