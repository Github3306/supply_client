package com.qtu.product_service.mapper;

import com.qtu.entity.BmGoods;
import com.qtu.entity.Brand;
import com.qtu.product_service.bean.SearchBoxBean;


import com.qtu.product_service.bean.Shop;
import com.qtu.product_service.bean.search;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;




import java.util.List;


@Mapper
public interface GoodsMapper {


//    List<SearchBoxBean> getListSearchBox(@Param("ppName") String ppName, @Param("spName") String spName);
    List<Brand> ceshi(String name);

    /**
     * 按品牌名称和商品名称搜索
     * @param ppName
     * @param ggName
     * @return
     */
    List<search> seacrchBox(@Param("ppName") String ppName,@Param("ggName") String ggName);

    /**
     * 最热商品
     * @return
     */
    List<search> getHotShopping();

    /**
     * 最新商品
     * @return
     */
    List<search> getNewShopping();

    List<search> oftenBuyList(@Param("tel") String tel);

    List<Shop> supplierShouYe(@Param("ckName") String ckName);

    List<Shop> supplierShouYeWeiHeZuo(@Param("ckName") String ckName);

    Integer updateHeZuoRelation(@Param("dpName") String dpName);
}