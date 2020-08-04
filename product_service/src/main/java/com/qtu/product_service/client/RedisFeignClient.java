package com.qtu.product_service.client;

import com.qtu.product_service.client.impl.RedisFeignClientImpl;
import com.qtu.util.CacheResult;
import com.qtu.util.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */

@FeignClient(value = "redis-service",fallback = RedisFeignClientImpl.class)
public interface RedisFeignClient {
    /*
    * redis服务：从redis中获取
    * */
    @RequestMapping("/get/{key}")
    public CacheResult getFromRedis(@PathVariable("key") String key);

    @RequestMapping("/set")
    public CacheResult setToRedis(@RequestParam("key") String key, @RequestBody Object value, @RequestParam("time") Long time);

    /**
     * 存储string类型到redis
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/setString")
    CacheResult setStringToRedis(@RequestParam("key") String key,@RequestParam("value") String value,@RequestParam("time") Long time);

}
