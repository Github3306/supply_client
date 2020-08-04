package com.qtu.product_service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qtu.product_service.client.RedisFeignClient;
import com.qtu.product_service.entity.BrandBean;
import com.qtu.product_service.entity.HotProduct;
import com.qtu.product_service.entity.HotProductInfoBean;
import com.qtu.product_service.entity.ProductInfoBean;
import com.qtu.product_service.mapper.Zswmapper;
import com.qtu.product_service.service.HotProductService;
import com.qtu.util.CacheResult;
import com.qtu.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
@Service
public class HotProuctServiceImpl implements HotProductService {
    @Autowired
    private Zswmapper zswmapper;
    @Autowired
    private RedisFeignClient redisFeignClient;
    //查询首页的热门商品图片
    @Override
    public List<HotProduct> getHotproduct() {
        if(redisFeignClient.getFromRedis("hotProduct").getData() == null){
            System.out.println("我进入了redis");
            List<HotProduct> list = zswmapper.selectHotProduct();
            CacheResult result = redisFeignClient.setToRedis("hotProduct", list, 1000 * 60 * 1000l);
        }
        return (List<HotProduct>) redisFeignClient.getFromRedis("hotProduct").getData();
    }

    //查询首页的热门商品的详细信息
    @Override
    public HotProductInfoBean getHotProductInfo(Integer id) {
        if( redisFeignClient.getFromRedis("hotProductInfo"+""+id).getData() == null){
            System.out.println("我进入了redis");
            HotProductInfoBean list = zswmapper.selectHotProductInfo(id);
            redisFeignClient.setToRedis("hotProductInfo"+""+id, list, 1000 * 60 * 1000l);
        }

        return (HotProductInfoBean) CacheResult.linkedHashMapToObject(redisFeignClient.getFromRedis("hotProductInfo"+""+id).getData(), HotProductInfoBean.class);
    }

    //查询更多商品的信息（使用分页）
    @Override
    public List<ProductInfoBean> getProductInfo(Integer pageNo,Integer pageSize) {
        if(pageNo == null){
            pageNo = 0;
            System.out.println("pageNo"+""+pageNo);
        }
        if(pageSize == null){
            pageSize = 10;
            System.out.println("pageSize"+""+pageSize);
        }
        if(redisFeignClient.getFromRedis("productInfo"+""+pageNo).getData() == null){
            System.out.println("我进入了redis");
            Page<Object> page = PageHelper.startPage(pageNo,pageSize);
            List<ProductInfoBean> list = zswmapper.selectProductInfo();
            CacheResult result = redisFeignClient.setToRedis("productInfo"+""+pageNo, list, 1000 * 60 * 1000l);
        }
        return (List<ProductInfoBean>) redisFeignClient.getFromRedis("productInfo"+""+pageNo).getData();
    }

    //查询新品信息
    @Override
    public List<HotProduct> getNewproduct() {
        if(redisFeignClient.getFromRedis("newProduct").getData() == null){
            System.out.println("我进入了redis");
            List<HotProduct> list = zswmapper.selectNewProduct();
            CacheResult result = redisFeignClient.setToRedis("newProduct", list, 1000 * 60 * 1000l);
        }
        return (List<HotProduct>) redisFeignClient.getFromRedis("newProduct").getData();
    }

    //查询品牌
    @Override
    public List<BrandBean> getBrand() {
        if(redisFeignClient.getFromRedis("brand").getData() == null){
            System.out.println("我进入了redis");
            List<BrandBean> list = zswmapper.selectCategory();
            CacheResult result = redisFeignClient.setToRedis("brand", list, 1000 * 60 * 1000l);
        }
        return (List<BrandBean>) redisFeignClient.getFromRedis("brand").getData();
    }

    //根据品牌信息查询相应的信息
    @Override
    public List<ProductInfoBean> getInfoByCategory(Integer id) {
        if(redisFeignClient.getFromRedis("ProductInfoBean"+""+id).getData() == null){
            System.out.println("我进入了redis");
            List<ProductInfoBean> list = zswmapper.selectInfoByCategory(id);
            CacheResult result = redisFeignClient.setToRedis("ProductInfoBean"+""+id, list, 1000 * 60 * 1000l);
        }
        return (List<ProductInfoBean>) redisFeignClient.getFromRedis("ProductInfoBean"+""+id).getData();
    }

}
