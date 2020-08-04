package com.qtu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Hu Shengkai
 * @create 2020-01-11 10:12
 */
@Data
public class CacheResult<T> {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;



    public static <T> CacheResult build(Integer status, String msg, T data) {
        return new CacheResult(status, msg, data);
    }

    public static <T> CacheResult ok  (T data) {
        return new CacheResult(data);
    }

    public static CacheResult ok() {
        return new CacheResult(null);
    }

    public CacheResult() {
    }

    public static CacheResult build(Integer status, String msg) {
        return new CacheResult(status, msg, null);
    }

    public CacheResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CacheResult(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    //linkedhashMap转对象
    public static <T> Object linkedHashMapToObject(Object map, Class<T> beanType) {
        try {
            String json = MAPPER.writeValueAsString(map);
            T t = MAPPER.readValue(json, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
