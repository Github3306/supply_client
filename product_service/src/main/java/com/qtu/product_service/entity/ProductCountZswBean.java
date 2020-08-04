package com.qtu.product_service.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
@Data
public class ProductCountZswBean {
    private String goodsName;
    private  Integer buyNum = 0;
    private BigDecimal amount = BigDecimal.valueOf(0);
}
