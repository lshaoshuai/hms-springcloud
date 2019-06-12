package com.hms.core.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hms.base.constant.MqConstant.*;

/**
 * @author luoshao
 * @date 2019/6/1 15:52
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

@Configuration
public class MqQueueConfig {



    @Bean
    public Queue orderQueue() {
        /**
         durable="true" 持久化消息队列 ， rabbitmq重启的时候不需要创建新的队列
         auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         exclusive  表示该消息队列是否只在当前connection生效,默认是false
         */
        return new Queue(ORDER_QUEUE,true,false,false);
    }

    @Bean
    public Queue codeQueue() {
        return new Queue(CODE_QUEUE,true,false,false);
    }

    @Bean
    public Queue roomQueue() {
        return new Queue(ROOM_QUEUE,true,false,false);
    }

    @Bean
    public Queue orderDelayQueue() {

        return QueueBuilder
                .durable(DELAY_ORDER_QUEUE)
                // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", ORDER_EXCHANGE) //DLX
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", ORDER_ROUTING_KEY)
                .build();
    }
}
