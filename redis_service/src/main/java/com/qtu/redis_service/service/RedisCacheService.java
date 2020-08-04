package com.qtu.redis_service.service;

import com.qtu.util.CacheResult;

import java.util.List;

/**
 * redis缓存接口业务
 * @author Hu Shengkai
 * @create 2020-01-10 14:03
 */
public interface RedisCacheService {
    /**
     * 从缓存中拿数据
     * @param key
     * @return
     */
    public <V> CacheResult get(String key);

    /**
     * 向缓存中设置数据（在redis中存储为string形式）
     * @param key
     * @param value
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    public CacheResult set(String key, Object value, long time);


    /**
     * 向缓存中设置string类型的数据
     * @param key
     * @param value
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    public CacheResult setString(String key, String value, long time);

    /**
     * 将list全部内容放入缓存(储存形式为ridis中的list类型)
     * @param key
     * @param value
     * @param time
     * @return
     */
    public CacheResult leftPushAllList(String key, List<Object> value, long time);

    /**
     * 把值存入到list中
     * @param key
     * @param value
     * @param time
     * @return
     */
    public CacheResult leftPushList(String key, Object value, long time);

    /**
     * 从list中取出值
     * @param key
     * @return
     */
    public CacheResult rightPopList(String key);

    /**
     * 递增
     * @param key
     * @param delta 递增多少
     * @return
     */
    public CacheResult incr(String key, long delta);

}
