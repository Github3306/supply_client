package com.qtu.product_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
@Data
public class ProductInfoBean {
    Integer count;//已采购数量
    Integer id;//商品id
    BigDecimal price;//商品价格
    String goodsImg;//商品图片
    String goodsName;//商品名字
    Integer categoryId;
}
