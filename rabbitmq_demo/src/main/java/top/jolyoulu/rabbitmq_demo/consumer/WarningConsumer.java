package top.jolyoulu.rabbitmq_demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.jolyoulu.rabbitmq_demo.config.ConfirmConfig;

/**
 * @Author: JolyouLu
 * @Date: 2021/10/27 10:11
 * @Version 1.0
 */
@Slf4j
@Component
public class WarningConsumer {

    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public void receiverConfirmMessage(Message message){
        log.error("接收到不可路由消息: {}",new String(message.getBody()));
    }

}
