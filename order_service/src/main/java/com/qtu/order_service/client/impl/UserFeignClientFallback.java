package com.qtu.order_service.client.impl;

import com.qtu.entity.BmMember;
import com.qtu.order_service.client.UserFeignClient;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Component;

/**
 * @author Hu Shengkai
 * @create 2020-01-10 1:03
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public MyResult queryMemberByToken(String token) {
        return null;
    }
}
