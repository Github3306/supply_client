package com.qtu.sendorders_service;

import com.qtu.sendorders_service.client.RedisFeignClient;
import com.qtu.util.CacheResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendordersServiceApplicationTests {
    @Autowired
    private RedisFeignClient redisFeignClient;

    @Test
    public void contextLoads() {
        CacheResult result = redisFeignClient.leftPushList("LOGISTICS_EMPLOYEE:W1100000001", 111, -1);
        System.out.println(result);
    }

}
