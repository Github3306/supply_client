package com.qtu.product_service.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */

@Data
public class HotProduct {
    private Integer id;
    private String goodsimg;
    private String goodsName;
    private BigDecimal price;
}
