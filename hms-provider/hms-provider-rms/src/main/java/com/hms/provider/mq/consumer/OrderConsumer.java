package com.hms.provider.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.hms.base.constant.MqConstant.ORDER_QUEUE;

/**
 * @author luoshao
 * @date 2019/6/1 16:07
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */


@Slf4j
@Component
public class OrderConsumer {

    /**
     * queues  指定从哪个队列（queue）订阅消息
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {ORDER_QUEUE})
    public void handleMessage(Message message,Channel channel) throws IOException {
        try {
            // 处理消息
            System.out.println("OrderConsumer {} handleMessage :"+message);
            System.out.println(new String(message.getBody()));
        }catch (Exception e){
            log.error("OrderConsumer  handleMessage {} , error:",message,e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }

}
