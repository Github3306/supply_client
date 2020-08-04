package com.qtu.sendorders_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 14:48
 */
@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "qtu.sendOrders.news";   //队列
    public static final String EXCHANGE = "qtu.exchange";   //路由
    public static final String KEY = "sendOrders";  //路由key

    /**
     * 消息队列发送消息时，对象的序列化配置
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 创建Direct（直连）模式队列
     * @return
     */
    @Bean
    public Queue directQueue() {
        // 第一个参数是队列名字， 第二个参数是指是否持久化
        return new Queue(QUEUE, true);
    }

    /**
     * 声明一个名为qtu.exchange的交换机
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    /**
     * 绑定关系
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(exchange()).with(KEY);
    }
}
