package per.luo.service.room.rabbitmq;

import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

/**
 * @author luoshao
 * @date 2019/5/15 11:21
 * @projectname HMS
 */

@Configuration
public class RabbitConfig {

    private final static String STRING = "ROOMQUEUE";

    public Queue queue() {
        return new Queue(STRING);
    }

}
