package com.qtu.user_service.client.impl;

import com.qtu.user_service.client.RedisFeignClient;
import com.qtu.user_service.sjyBean.TaotaoResult;
import com.qtu.util.CacheResult;
import org.springframework.stereotype.Component;

/**
 *  @auther: Shi jy
 *  @Date: 2020/1/10 16:56
 */
@Component
public class RedisFeignClientImpl implements RedisFeignClient {
    @Override
    public CacheResult getFromRedis(String token) {

        return CacheResult.build(400,"getFromRedis请求熔断了……");
    }

    @Override
    public TaotaoResult setToRedis(String token, Object value,Long time) {

        return TaotaoResult.build(400,"getFromRedis请求熔断了……");
    }
}
