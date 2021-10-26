package top.jolyoulu.rabbitmq_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @Author: JolyouLu
 * @Date: 2021/10/26 10:40
 * @Version 1.0
 */
@Configuration
public class TTLQueueConfig {
    //普通交换机的名称
    public static final String X_EXCHANGE = "X";
    //死信交换机名称
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    //普通队列名称
    public static final String A_QUEUE = "QA";
    public static final String B_QUEUE = "QB";
    public static final String C_QUEUE = "QC";
    //死信队列名称
    public static final String DEAD_LETTER_QUEUE = "QD";

    //声明xExchange(普通交换机)
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

    //声明yExchange(死信交换机)
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    //声明QA(普通队列)
    @Bean("aQueue")
    public Queue aQueue(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE); //设置队列消息过期后转发到的死信交换机
        arguments.put("x-dead-letter-routing-key","YD"); //设置RoutingKey
        arguments.put("x-message-ttl",10000); //设置队列TTL
        //arguments.put("x-max-length",6); //设置队列最大容量
        return QueueBuilder
                .durable(A_QUEUE) //durable持久化、nonDurable不持久化
                .withArguments(arguments)
                .build();
    }

    //声明QB(普通队列)
    @Bean("bQueue")
    public Queue bQueue(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE); //设置队列消息过期后转发到的死信交换机
        arguments.put("x-dead-letter-routing-key","YD"); //设置RoutingKey
        arguments.put("x-message-ttl",40000); //设置队列TTL
        //arguments.put("x-max-length",6); //设置队列最大容量
        return QueueBuilder
                .durable(B_QUEUE) //durable持久化、nonDurable不持久化
                .withArguments(arguments)
                .build();
    }

    //声明QC(普通队列)
    @Bean("cQueue")
    public Queue cQueue(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE); //设置队列消息过期后转发到的死信交换机
        arguments.put("x-dead-letter-routing-key","YD"); //设置RoutingKey
        //arguments.put("x-message-ttl",40000); //设置队列TTL
        //arguments.put("x-max-length",6); //设置队列最大容量
        return QueueBuilder
                .durable(C_QUEUE) //durable持久化、nonDurable不持久化
                .withArguments(arguments)
                .build();
    }

    //声明QD(死信队列)
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return QueueBuilder
                .durable(DEAD_LETTER_QUEUE) //durable持久化、nonDurable不持久化
                .build();
    }

    //绑定普通交换机与普通队列关系
    @Bean
    public Binding aQueueBindingX(@Qualifier("aQueue") Queue aQueue,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(aQueue).to(xExchange).with("XA");
    }

    @Bean
    public Binding bQueueBindingX(@Qualifier("bQueue") Queue bQueue,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(bQueue).to(xExchange).with("XB");
    }

    @Bean
    public Binding cQueueBindingX(@Qualifier("cQueue") Queue cQueue,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(cQueue).to(xExchange).with("XC");
    }

    @Bean
    public Binding deadLetterQueueBindingY(@Qualifier("deadLetterQueue") Queue deadLetterQueue,
                                           @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(deadLetterQueue).to(yExchange).with("YD");
    }
}
