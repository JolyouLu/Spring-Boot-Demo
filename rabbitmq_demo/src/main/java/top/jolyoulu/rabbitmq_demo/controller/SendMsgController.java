package top.jolyoulu.rabbitmq_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.rabbitmq_demo.config.TTLQueueConfig;

import java.time.LocalDateTime;

/**
 * @Author: JolyouLu
 * @Date: 2021/10/26 11:10
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发消息
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message){
        log.info("当前时间: {},发送一条消息给2个TTL队列: {}", LocalDateTime.now().toString(),message);
        rabbitTemplate.convertAndSend(TTLQueueConfig.X_EXCHANGE,
                "XA","消息来自ttl为10s的队列"+message);
        rabbitTemplate.convertAndSend(TTLQueueConfig.X_EXCHANGE,
                "XB","消息来自ttl为40s的队列"+message);
    }

}
