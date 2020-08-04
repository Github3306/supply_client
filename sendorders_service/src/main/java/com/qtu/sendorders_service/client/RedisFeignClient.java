package com.qtu.sendorders_service.client;

import com.qtu.sendorders_service.client.impl.RedisFeignClientImpl;
import com.qtu.util.CacheResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hu Shengkai
 * @create 2020-01-10 14:39
 */
@FeignClient(value = "redis-service", fallback = RedisFeignClientImpl.class)
public interface RedisFeignClient {

    /**
     * redis服务：从redis中取
     *
     * @param key
     * @return
     */
    @RequestMapping("/get/{key}")
    public CacheResult getFromRedis(@PathVariable("key") String key);

    /**
     * 往redis中存储对象
     * @param key
     * @param value
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    @RequestMapping("/set")
    public CacheResult setToRedis(@RequestParam("key") String key,
                                  @RequestBody Object value,
                                  @RequestParam("time") Long time);

    /**
     * 存储string类型到redis
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/setString")
    public CacheResult setStringToRedis(@RequestParam("key") String key,
                                        @RequestParam("value") String value,
                                        @RequestParam("time") Long time);

    /**
     * 将list全部内容放入缓存(储存形式为ridis中的list类型)
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/lpushAll")
    public CacheResult leftPushAllList(@RequestParam("key") String key,
                                       @RequestBody List<Object> value,
                                       @RequestParam("time") long time);

    /**
     * 把值存入到list中
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/lpush")
    public CacheResult leftPushList(@RequestParam("key") String key,
                                    @RequestBody Object value,
                                    @RequestParam("time") long time);

    /**
     * 从list中取出值
     * @param key
     * @return
     */
    @RequestMapping("/lpop")
    public CacheResult rightPopList(@RequestParam("key") String key);

}