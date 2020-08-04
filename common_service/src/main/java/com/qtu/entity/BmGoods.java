package com.qtu.entity;

import com.qtu.bean.CacheModel;
import lombok.Data;

import java.util.Date;

@Data
public class BmGoods extends CacheModel {
    private Integer id;

    private String goodsCode;

    private String goodsName;

    private String warehouseCode;

    private Integer categoryId;

    private Integer brandId;

    private Integer sort;

    private String goodsDetails;

    private Date createTime;

    private Integer isDeleted;

    private String goodsMark;
}