package com.qtu.redis_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RedisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisServiceApplication.class, args);
    }

}
