package top.jolyoulu.rabbitmq_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.rabbitmq_demo.config.DelayedQueueConfig;
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
    public void sendMsg(@PathVariable("message") String message) {
        log.info("当前时间: {},发送一条消息给2个TTL队列: {}", LocalDateTime.now().toString(), message);
        rabbitTemplate.convertAndSend(TTLQueueConfig.X_EXCHANGE,
                "XA", "消息来自ttl为10s的队列" + message);
        rabbitTemplate.convertAndSend(TTLQueueConfig.X_EXCHANGE,
                "XB", "消息来自ttl为40s的队列" + message);
    }

    //发消息指定时间
    @GetMapping("/sendTTLMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable("message") String message, @PathVariable("ttlTime") String ttlTime) {
        log.info("当前时间: {},发送一条消息给1条,TTL为{}毫秒,信息给队列: {}", LocalDateTime.now().toString(), ttlTime, message);
        rabbitTemplate.convertAndSend(TTLQueueConfig.X_EXCHANGE,
                "XC", message, (msg) -> {
                    //设置ttl时间
                    msg.getMessageProperties().setExpiration(ttlTime);
                    return msg;
                });
    }

    //发消息基于插件的延迟消息
    @GetMapping("/sendDelayedMsg/{message}/{delayedTime}")
    public void sendMsg(@PathVariable("message") String message, @PathVariable("delayedTime") Integer delayedTime) {
        log.info("当前时间: {},发送一条消息给1条,延迟为{}毫秒,信息给队列: {}", LocalDateTime.now().toString(), delayedTime, message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE,
                DelayedQueueConfig.DELAYED_ROUTING_KEY,
                message, (msg) -> {
                    //设置延迟时间
                    msg.getMessageProperties().setDelay(delayedTime);
                    return msg;
                });
    }
}
