package com.qtu.product_service.controller;

import com.github.pagehelper.PageInfo;
import com.qtu.product_service.bean.SearchBoxBean;
import com.qtu.product_service.bean.Shop;
import com.qtu.product_service.bean.search;
import com.qtu.product_service.service.SearchBoxService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chb
 * @date 2020/1/10 - 9:15
 **/
@Controller
@RequestMapping(value="/search")
public class GoodsController {
    @Autowired
    private SearchBoxService searchBoxService;

    @ResponseBody
    @RequestMapping(value="/listSearchBox",method = RequestMethod.GET)
    public List<search> getListSearchBox(Integer pageNo,Integer pageSize,String keyWord){

        PageInfo<search> listSearchBoxBean = searchBoxService.getPageInfoBox(pageNo,pageSize,keyWord);
        return listSearchBoxBean.getList();
    }
    @ResponseBody
    @RequestMapping(value="/hotShopping",method = RequestMethod.GET)
    public List<search> getHotShopping(Integer pageNo,Integer pageSize){
       PageInfo<search> hotShopping = searchBoxService.getPageInfoHot(pageNo,pageSize);
//        List<search> hotShopping = searchBoxService.getHotShopping();
        return hotShopping.getList();
    }

    @ResponseBody
    @RequestMapping(value="/newShopping",method = RequestMethod.GET)
    public List<search> getNewShopping(Integer pageNo,Integer pageSize){
        PageInfo<search> newShopping = searchBoxService.getPageInfoNew(pageNo,pageSize);
        return newShopping.getList();
    }
    @ResponseBody
    @RequestMapping(value="/oftenBuyList",method = RequestMethod.POST)
    public List<search> getOftenBuyList(HttpServletRequest request,Integer pageNo, Integer pageSize){
        PageInfo<search> pageInfoOften = searchBoxService.getPageInfoOften(request,pageNo, pageSize);
//        List<search> searches = searchBoxService.oftenBuyList("123");
        return pageInfoOften.getList();
    }

    @ResponseBody
    @RequestMapping(value="/supplierShouYe",method = RequestMethod.POST)
    public List<Shop> getSupplierShouYe(){
        List<Shop> supplierShouYe = searchBoxService.getSupplierShouYe();
        return supplierShouYe;
    }

    @ResponseBody
    @RequestMapping(value="/supplierShouYeWeiHeZuo",method = RequestMethod.POST)
    public List<Shop> getSupplierShouYeWeiHeZuo(){
        List<Shop> supplierShouYeWeiHeZuo = searchBoxService.getSupplierShouYeWeiHeZuo();
        return supplierShouYeWeiHeZuo;
    }
    @ResponseBody
    @RequestMapping(value="/updateHeZuoRelation",method = RequestMethod.POST)
    public Map<String,Object> updateHeZuoRelation(String dpName){
        Map<String, Object> map = searchBoxService.updateHeZuoRelation(dpName);
        return map;
    }
}
