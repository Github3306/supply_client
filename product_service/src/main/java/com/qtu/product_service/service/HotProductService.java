package com.qtu.product_service.service;

import com.qtu.product_service.entity.BrandBean;
import com.qtu.product_service.entity.HotProduct;
import com.qtu.product_service.entity.HotProductInfoBean;
import com.qtu.product_service.entity.ProductInfoBean;
import com.qtu.product_service.mapper.Zswmapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
public interface HotProductService {

    //首页显示热销商品信息
    public List<HotProduct> getHotproduct();

    //首页热销商品的详细信息
    public HotProductInfoBean getHotProductInfo(Integer id);

    //展示更多商品的商品信息
    public List<ProductInfoBean> getProductInfo(Integer pageNo,Integer pageSize);

    //展示新品的信息
    public List<HotProduct> getNewproduct();

    //展示品牌信息
    public List<BrandBean> getBrand();

    //根据品牌查询对应的商品
    public List<ProductInfoBean>getInfoByCategory(Integer id);
}
