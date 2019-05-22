package com.hms.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoshao
 * @date 2019/5/19 12:34
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Configuration
public class SenderConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue");
    }
}
