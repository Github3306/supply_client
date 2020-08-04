package com.qtu.redis_service.controller;

import com.qtu.bean.CacheModel;
import com.qtu.redis_service.service.RedisCacheService;
import com.qtu.util.ExceptionUtil;
import com.qtu.util.JsonUtils;
import com.qtu.util.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * redis服务接口
 * @author Hu Shengkai
 * @create 2020-01-10 14:10
 */
@RestController
public class RedisCacheController {
    @Autowired
    private RedisCacheService redisCacheService;

    /**
     * 从redis中取
     * @param key
     * @return
     */
    @RequestMapping("/get/{key}")
    public CacheResult getFromRedis(@PathVariable("key") String key) {
        CacheResult result = null;
        try {
            result = redisCacheService.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 往redis中存储
     * @param key
     * @param value
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    @RequestMapping("/set")
    public CacheResult setToRedis(@RequestParam("key") String key,
                               @RequestBody Object value,
                               @RequestParam("time") Long time){
        /*
            feign接过来的object类性 都会默认转换为linkedHashMap
         */
        CacheResult result = null;
        try {
            System.out.println("存储对象的类型："+value.getClass());
            result = redisCacheService.set(key, value, time);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 存string类型到redis
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/setString")
    public CacheResult setStringToRedis(@RequestParam("key") String key,
                                     @RequestParam("value") String value,
                                     @RequestParam("time") Long time){
        CacheResult result = null;
        try {
            result = redisCacheService.setString(key, value, time);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 将list全部内容放入缓存(储存形式为ridis中的list类型)
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/lpushAll")
    public CacheResult leftPushAllList(@RequestParam("key") String key, @RequestBody List<Object> value, @RequestParam("time") long time){
        CacheResult result = null;
        try {
            result = redisCacheService.leftPushAllList(key, value, time);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 把值存入到list中
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("/lpush")
    public CacheResult leftPushList(@RequestParam("key") String key, @RequestBody Object value, @RequestParam("time") long time){
        CacheResult result = null;
        try {
            result = redisCacheService.leftPushList(key, value, time);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 从list中取出值
     * @param key
     * @return
     */
    @RequestMapping("/lpop")
    public CacheResult rightPopList(@RequestParam("key") String key) {
        CacheResult result = null;
        try {
            result = redisCacheService.rightPopList(key);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    @RequestMapping("/incr")
    public CacheResult incr(@RequestParam("key") String key, @RequestParam("delta") long delta) {
        CacheResult result = null;
        try {
            result = redisCacheService.incr(key, delta);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }
}
