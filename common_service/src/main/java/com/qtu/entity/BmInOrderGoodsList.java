package com.qtu.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BmInOrderGoodsList {
    private Integer id;

    private String orderId; //订单id

    private Integer goodsId; //商品id

    private Integer spId; //规格id

    private String sname; //规格名称

    private String goodsName; //商品名称

    private String goodsImg; //商品图片

    private Integer buyNum; //购买数量

    private BigDecimal price; //购买金额

    private Integer pickingNum; //配货数量

    private Integer loadingNum; //装货数量

    private Integer examineNum; //验货数量

    private Integer deliveryNum; //交货数量

}