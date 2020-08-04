package com.qtu.redis_service.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitCache {

    @PostConstruct
    public void getAllCache(){
        System.out.println("加载所有缓存信息");
    }
}
