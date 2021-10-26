package top.jolyoulu.rabbitmq_demo.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.jolyoulu.rabbitmq_demo.config.DelayedQueueConfig;

import java.time.LocalDateTime;

/**
 * @Author: JolyouLu
 * @Date: 2021/10/26 11:25
 * @Version 1.0
 */
@Slf4j
@Component
public class DelayedQueueConsumer {

    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void receiveD(Message message, Channel channel) throws Exception{
        String msg = new String(message.getBody());
        log.info("当前时间: {},收到延迟消息: {}", LocalDateTime.now().toString(),msg);
    }
}
