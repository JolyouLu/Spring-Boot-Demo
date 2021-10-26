package top.jolyoulu.rabbitmq_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/10/26 15:59
 * @Version 1.0
 */
@Configuration
public class DelayedQueueConfig {
    //交换机
    public static final String DELAYED_EXCHANGE = "delayed.exchange";
    //队列
    public static final String DELAYED_QUEUE = "delayed.queue";
    //routingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routing.key";

    //声明交换机(由于这是插件来的所以需要使用自定义交换机构建)
    @Bean
    public CustomExchange delayedExchange(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","direct"); //交换机为直接交换机

        return new CustomExchange(DELAYED_EXCHANGE, //交换机名称
                "x-delayed-message", //交换机类型
                true, //是否持久化
                false, //是否自动删除
                arguments);
    }

    //声明队列
    @Bean
    public Queue delayedQueue(){
        return QueueBuilder
                .durable(DELAYED_QUEUE) //durable持久化、nonDurable不持久化
                .build();
    }

    //交换机绑定队列
    @Bean
    public Binding delayedQueueBindingDelayedExchange(@Qualifier("delayedQueue") Queue delayedQueue,
                                                      @Qualifier("delayedExchange") CustomExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
