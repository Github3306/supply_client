package com.qtu.order_service;

import com.qtu.order_service.config.MyAMQPConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 14:17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test1(){
        rabbitTemplate.convertAndSend(MyAMQPConfig.EXCHANGE, MyAMQPConfig.KEY, "测试测试测试测试");
    }
}
