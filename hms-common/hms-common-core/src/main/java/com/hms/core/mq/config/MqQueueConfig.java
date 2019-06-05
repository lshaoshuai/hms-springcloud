package com.hms.core.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
    public Queue secondQueue() {
        return new Queue(QUEUE_NAME2,true,false,false);
    }

    @Bean
    public Queue thirdQueue() {
        // 配置 自动删除
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 60000);//60秒自动删除
        return new Queue(QUEUE_NAME3,true,false,true,arguments);
    }
}
