package com.qtu.sendorders_service.client.impl;

import com.qtu.sendorders_service.client.RedisFeignClient;
import com.qtu.util.CacheResult;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return CacheResult.build(400,"setStringToRedis请求熔断了……");
    }

    @Override
    public CacheResult leftPushAllList(String key, List<Object> value, long time) {
        return CacheResult.build(400,"leftPushAllList请求熔断了……");
    }

    @Override
    public CacheResult leftPushList(String key, Object value, long time) {
        return CacheResult.build(400,"leftPushList请求熔断了……");
    }

    @Override
    public CacheResult rightPopList(String key) {
        return CacheResult.build(400,"leftPopList请求熔断了……");
    }
}
