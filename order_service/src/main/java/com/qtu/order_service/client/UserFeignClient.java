package com.qtu.order_service.client;

import com.qtu.entity.BmMember;
import com.qtu.order_service.client.impl.UserFeignClientFallback;
import com.qtu.util.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * user服务FeignClient
 * @author Hu Shengkai
 * @create 2020-01-10 1:01
 */
@FeignClient(value = "user-service", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/member/{token}")
    MyResult queryMemberByToken(@PathVariable("token") String token);
}
