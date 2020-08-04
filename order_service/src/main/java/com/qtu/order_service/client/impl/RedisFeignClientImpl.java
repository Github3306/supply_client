package com.qtu.order_service.client.impl;

import com.qtu.bean.CacheModel;
import com.qtu.order_service.client.RedisFeignClient;
import com.qtu.util.CacheResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Hu Shengkai
 * @create 2020-01-10 14:40
 */
@Component
public class RedisFeignClientImpl implements RedisFeignClient {
    @Override
    public CacheResult getFromRedis(String key) {
        return CacheResult.build(400,"getFromRedis请求熔断了……");
    }

    @Override
    public CacheResult setToRedis(String key, Object value, Long time) {
        return CacheResult.build(400,"setToRedis请求熔断了……");
    }

    @Override
    public CacheResult setStringToRedis(String key, String jsonValue, Long time) {
        return CacheResult.build(400,"setToRedis请求熔断了……");
    }
}
