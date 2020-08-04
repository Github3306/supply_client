package com.qtu.product_service.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
@Data
public class HotProductInfoBean {
    int count;//商品购买数量
    String cname;//商品种类
    String bname;//品牌名字
    String mechanismName;//供应商
    BigDecimal price;//价钱
    String goodsImg;//商品图片
    String goodsName;//商品名字
    int id;
}
