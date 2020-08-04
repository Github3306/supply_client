package com.qtu.product_service.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
/*
* goodsStatistics的所有字段
* */
@Data
public class Zbean {

    private String goodsName;
    private  Integer buyNum = 0;
    private BigDecimal amount = BigDecimal.valueOf(0);
    private  Integer buyNumError = 0;
    private BigDecimal amountError = BigDecimal.valueOf(0);
}
