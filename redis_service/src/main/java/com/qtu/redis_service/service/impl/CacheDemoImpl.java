package com.qtu.redis_service.service.impl;

import com.qtu.redis_service.service.CacheDemo;
import com.qtu.redis_service.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hu Shengkai
 * @create 2020-01-09 11:12
 */
@Service
public class CacheDemoImpl implements CacheDemo {

    //注入redisUtil
    @Autowired
    private RedisUtil redisUtil;

    //进行redis操作即可
}
