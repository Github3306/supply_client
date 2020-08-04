package com.qtu.product_service.entity;

import lombok.Data;

/**
 * @author 张显
 * @create 2020-01-10 9:06
 */
@Data
public class GoodsInfoBean {
    /*商品表*/
    //private Integer id;//商品ID
    private String goodsCode;//商品代码
    private String goodsName;//商品名称
    private String warehouseCode;//仓库代码
    private Integer categoryId;//商品种类id
    private Integer brandId;//商品品牌id
    private String goodsDetails;//商品详情
    private String goodsMark;//商品描述
    private Integer isDeleted;//删除标记 0.未删除 1.已删除

    /*品牌表*/
    private String bname;//品牌名称
    private String brandImg;//品牌图片

    /*商品详情表*/
    private Integer id;//商品规格id
    private String sname;//规格名称
    private Double oldPrice;//原价
    private Double price;//现价
    private Integer stock;//库存
    private Integer warnStock;//警戒库存
    private Integer allSaled;//总销量
    private String currentMonth;//月销量
    private Integer isShelves;//是否上架 0.未上架 1.上架
    private Integer isRecom;//是否推荐 0.不推荐 1.推荐
    private String goodsImgUrl;//商品图片
    private String qualityPeriod;//保质期

    private String mechanismName;//供应商

    private Integer zonghe;
    private Integer jiage;
    private Integer xiaoliang;

    private String cname;

    private Integer buyNum;
}
