package com.qtu.order_service.entity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车商品项
 * @author Hu Shengkai
 * @create 2020-01-09 23:07
 */
@Data
public class CartItem implements Serializable {
    private Integer id; //商品规格id

    private Integer goodsId; //商品id

    private String goodsName; //商品名称

    private String mechanismName; //机构名称

    private String mechanismCode; //机构编号

    private BigDecimal price; //商品价格

    private BigDecimal totalPrice; //商品价格

    private String goodsImgUrl; //商品图片

    private Integer goodsNum; //商品数量

    private Integer stock; //库存
}
