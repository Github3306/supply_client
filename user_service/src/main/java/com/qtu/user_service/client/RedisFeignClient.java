package com.qtu.user_service.client;

import com.qtu.user_service.client.impl.RedisFeignClientImpl;
import com.qtu.user_service.sjyBean.TaotaoResult;
import com.qtu.util.CacheResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  @auther: Shi jy
 *  @Date: 2020/1/10 16:49
 */
@FeignClient(value="redis-service", fallback = RedisFeignClientImpl.class)
public interface RedisFeignClient {

    /**
     *  @Explain： redis服务，从redis中取token
     *  @Author Shi jy
     *  @Date 2020/1/10 16:52
     *  @Param
    */
    @RequestMapping("/get/{key}")
    public CacheResult getFromRedis(@PathVariable("key")String key);


    /**
     *  @Explain: 往redis中存储
     *  @Author Shi jy
     *  @Date 2020/1/10 16:54
     *  @Param
    */
    @RequestMapping("/set")
    public TaotaoResult setToRedis(@RequestParam("key")String key , @RequestBody Object value, @RequestParam("time") Long time);


}
