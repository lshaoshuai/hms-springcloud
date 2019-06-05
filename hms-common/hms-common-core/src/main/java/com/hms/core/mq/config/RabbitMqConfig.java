package com.hms.core.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hms.base.constant.MqConstant.ORDER_ROUTING_KEY;

/**
 * @author luoshao
 * @date 2019/6/1 16:01
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

@Configuration
public class RabbitMqConfig {
    /**
     * key: queue在该direct-exchange中的key值，当消息发送给direct-exchange中指定key为设置值时，
     * 消息将会转发给queue参数指定的消息队列
     */
    /** 队列key1*/


    @Autowired
    private MqQueueConfig queueConfig;

    @Autowired
    private ExchangeConfig exchangeConfig;

    /**
     * 将消息队列1和交换机1进行绑定,指定队列key1
     */
    @Bean
    public Binding binding_order_queue() {
        return BindingBuilder.bind(queueConfig.orderQueue()).to(exchangeConfig.directExchange()).with(ORDER_ROUTING_KEY);
    }
}
