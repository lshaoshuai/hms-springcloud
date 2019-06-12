package com.hms.core.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hms.base.constant.MqConstant.*;

/**
 * @author luoshao
 * @date 2019/6/1 15:51
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Configuration
public class ExchangeConfig {

    /**
     *   1.定义direct exchange，绑定first_exchange
     *   2.durable="true" 持久化交换机， rabbitmq重启的时候不需要创建新的交换机
     *   3.direct交换器相对来说比较简单，匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     *     fanout交换器中没有路由键的概念，他会把消息发送到所有绑定在此交换器上面的队列中。
     *     topic交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     */
    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange(ORDER_EXCHANGE, true, false);
        return directExchange;
    }

    /**
     * 所有的消息都会被广播发送到队列上
     * @return
     */
    @Bean
    public DirectExchange delayExchange(){

        return (DirectExchange) ExchangeBuilder
                .directExchange(DELAY_ORDER_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public DirectExchange roomExchange(){

        return (DirectExchange) ExchangeBuilder
                .directExchange(ROOM_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public DirectExchange codeDirectExchange() {
        DirectExchange directExchange = new DirectExchange(CODE_EXCHANGE, true, false);
        return directExchange;
    }
}
