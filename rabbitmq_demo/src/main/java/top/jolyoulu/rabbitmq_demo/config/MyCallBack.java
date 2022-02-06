package top.jolyoulu.rabbitmq_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.nio.cs.ext.MS874;

import javax.annotation.PostConstruct;

/**
 * @Author: JolyouLu
 * @Date: 2021/10/27 10:36
 * @Version 1.0
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //MyCallBack实例化后立刻执行的方法
    @PostConstruct
    public void init() {
        //将该实现类注入到RabbitTemplate中
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    //交换机确认回调方法
    //触发条件：
    //      1.交换机接收到回调(correlationData 回调消息id以及相关信息，ack true，cause null)
    //      2.交换机接收失败回调(correlationData 回调消息id以及相关信息，ack true，cause 失败的原因)
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机接收收消息成功，ID: {}", id);
        } else {
            log.error("交换机接收收消息失败，ID: {}，原因: {}", id, cause);
        }
    }

    //当消息传递过程中，消息不可达目的地的消息返回给生产者(成功的不会执行该回调)
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息: {}，被交换机 {} 回退，退回原因: {}，路由Key: {}",
                new String(message.getBody()), exchange, replyText, routingKey);
    }
}
