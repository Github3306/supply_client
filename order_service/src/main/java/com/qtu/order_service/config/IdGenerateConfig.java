package com.qtu.order_service.config;

import com.qtu.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hu Shengkai
 * @create 2020-01-17 10:34
 */
@Configuration
public class IdGenerateConfig {
    @Value("${keyGenerator.snowflake.workerId}")
    private long workerId;
    @Value("${keyGenerator.snowflake.datacenterId}")
    private long datacenterId;

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(){
        return new SnowflakeIdWorker(workerId,datacenterId);
    }
}
