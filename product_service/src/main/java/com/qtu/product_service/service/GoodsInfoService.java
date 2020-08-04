package com.qtu.product_service.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtu.entity.Category;
import com.qtu.product_service.entity.GoodsInfoBean;

import java.util.List;

/**
 * @author 张显
 * @create 2020-01-10 9:51
 */
public interface GoodsInfoService {

    /*得到查询商品信息*/
    PageInfo getSelectGoodsInfo(Integer pageNo, Integer pageNum, String bname, String goodsName);

    /*得到所有的父类目*/
    List<Category> getSelectAllPCategoryInfo();

    /*得到parentId下的所有子类目*/
    List<Category> getAllChildCategoryByPId(Integer categoryId);

    /*得到父类目所对应的商品信息*/
    PageInfo getSelectGoodsInfoByCateId(Integer pageNo, Integer pageNum,Integer id);

    /*根据商品详情id得到商品详情信息*/
    GoodsInfoBean getSelectGoodsInfoBySid(Integer sid);

    /*得到父类目所对应的商品信息*/
    PageInfo getSelectGoodsInfoByCateIds(Integer pageNo, Integer pageNum,Integer id,Integer zonghe,Integer jiage,Integer xiaoliang);

    /*得到父类目所对应的商品信息*/
    PageInfo getSelectGoodsInfoByCateIdss(Integer pageNo, Integer pageNum,Integer id,Integer zonghe,Integer jiage,Integer xiaoliang);

}
