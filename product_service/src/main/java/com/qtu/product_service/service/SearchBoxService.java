package com.qtu.product_service.service;

import com.github.pagehelper.PageInfo;
import com.qtu.product_service.bean.SearchBoxBean;
import com.qtu.product_service.bean.Shop;
import com.qtu.product_service.bean.search;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chb
 * @date 2020/1/10 - 14:46
 **/
public interface SearchBoxService {
    /**
     * 搜索
     * @param keyWord
     * @return
     */
    List<search> getListSearchBox(String keyWord);

    /**
     * 搜索 分页
     * @param pageNo
     * @param pageSize
     * @param keyWord
     * @return
     */
    PageInfo<search> getPageInfoBox(Integer pageNo,Integer pageSize,String keyWord);

    /**
     * 最热商品
     * @return
     */
    List<search> getHotShopping();
    /**
     * 最新商品
     */
    List<search> getNewShopping();

    /**
     * 热门商品分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<search> getPageInfoHot(Integer pageNo,Integer pageSize);

    /**
     * 新增商品分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<search> getPageInfoNew(Integer pageNo,Integer pageSize);

    List<search> oftenBuyList(String tel);

    PageInfo<search> getPageInfoOften(HttpServletRequest request,Integer pageNo, Integer pageSize);

    List<Shop> getSupplierShouYe();

    List<Shop> getSupplierShouYeWeiHeZuo();

    Map<String,Object> updateHeZuoRelation(String dpName);
}
