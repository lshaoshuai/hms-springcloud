package com.hms.provider.mq.consumer;

import com.hms.provider.service.EmailService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

import static com.hms.base.constant.MqConstant.CODE_QUEUE;

/**
 * @author luoshao
 * @date 2019/6/5 10:58
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Slf4j
@Component
public class CodeConsumer {

    @Resource
    EmailService emailService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * queues  指定从哪个队列（queue）订阅消息
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {CODE_QUEUE})
    public void handleMessage(Message message, Channel channel) throws IOException {
        try {
            // 处理消息
            logger.info("code消费者接收消息");
//            emailService.sendSimpleMail("1939125539@qq.com","测试邮件",new String(message.getBody()));
            logger.info("获取的验证码为:{}",new String(message.getBody(),"utf-8"));
        }catch (Exception e){
            log.error("OrderConsumer  handleMessage {} , error:",message,e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }
}
