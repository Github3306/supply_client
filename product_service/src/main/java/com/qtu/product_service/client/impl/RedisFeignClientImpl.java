package com.qtu.product_service.client.impl;

import com.qtu.product_service.client.RedisFeignClient;
import com.qtu.util.CacheResult;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Component;
import sun.misc.Cache;

/**
 * @author 张显
 * @create 2019-10-23 19:52
 */
@Component
public class RedisFeignClientImpl implements RedisFeignClient {

    @Override
    public CacheResult getFromRedis(String key) {
        return CacheResult.build(400,"getFromRedis请求熔断了。。。");
    }

    @Override
    public CacheResult setToRedis(String key, Object value, Long time) {
        return CacheResult.build(400,"setToRedis请求熔断了……");
    }

    @Override
    public CacheResult setStringToRedis(String key, String value, Long time) {
        return CacheResult.build(400,"getFromRedis请求熔断了……");
    }
}
