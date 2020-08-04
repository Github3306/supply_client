package com.qtu.product_service.controller;

import com.github.pagehelper.PageInfo;
import com.qtu.entity.Category;
import com.qtu.product_service.entity.GoodsInfoBean;
import com.qtu.product_service.service.GoodsInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张显
 * @create 2020-01-10 9:52
 */
@RestController
@RequestMapping(value = "/goodsInfo")
public class GoodsInfoController {

    @Resource
    private GoodsInfoService goodsInfoService;

    /**
     * 得到商品信息
     * @param pageNo
     * @param pageNum
     * @return
     */
    @GetMapping("/getGoodsInfo")
    public Object getGoodsInfos(Integer pageNo, Integer pageNum,String keyWords){
        String bname=keyWords;
        String goodsName=keyWords;
        PageInfo pageInfoList = goodsInfoService.getSelectGoodsInfo(pageNo, pageNum, bname, goodsName);
        return pageInfoList;
    }

    /**
     * 得到所有的父类目名称
     * @return
     */
    @PostMapping(value = "/pCategory")
    public Object getAllPCategoryList(){
        Map<String,Object> map=new HashMap<>();
        List<Category> info = goodsInfoService.getSelectAllPCategoryInfo();
        map.put("data",info);
        return map;
    }

    /**
     * 得到parentId下的所有子类目
     * @param
     * @return
     */
    @PostMapping(value = "/childCategoryByPId")
    public Object getAllChildCategoryByParentId(Integer categoryId){
        Map<String,Object> map=new HashMap<>();
        List<Category> childCategory = goodsInfoService.getAllChildCategoryByPId(categoryId);
        map.put("data",childCategory);
        return map;
    }

    /**
     * 得到父类目所对应的商品信息
     * @param id
     * @return
     */
    @GetMapping(value = "/goodsInfoByCategory")
    public Object getGoodsInfoByCategoryId(Integer pageNo, Integer pageNum,Integer id){
        Map<String,Object> map=new HashMap<>();
        PageInfo info = goodsInfoService.getSelectGoodsInfoByCateId(pageNo, pageNum, id);
        return info;
    }

    @GetMapping(value = "/goodsInfoByCategorys")
    public Object getGoodsInfoByCategoryIds(String token,Integer pageNo, Integer pageNum,Integer id,Integer zonghe,Integer jiage,Integer xiaoliang){
        Map<String,Object> map=new HashMap<>();
        PageInfo info = goodsInfoService.getSelectGoodsInfoByCateIds(pageNo, pageNum, id,zonghe,jiage,xiaoliang);
        return info;
    }

    @GetMapping(value = "/goodsInfoByCategoryss")
    public Object getGoodsInfoByCategoryIdss(String token,Integer pageNo, Integer pageNum,Integer id,Integer zonghe,Integer jiage,Integer xiaoliang){
        Map<String,Object> map=new HashMap<>();
        PageInfo info = goodsInfoService.getSelectGoodsInfoByCateIdss(pageNo, pageNum, id,zonghe,jiage,xiaoliang);
        return info;
    }

    /**
     * 根据商品详情id得到商品详情信息
     * @param sid
     * @return
     */
    @GetMapping(value = "/goodsInfoBySid")
    public Object getGoodsInfoBySid(String token,Integer sid,Integer promotionId){
        Map<String,Object> map=new HashMap<>();
        GoodsInfoBean infoBySid = goodsInfoService.getSelectGoodsInfoBySid(sid);
        map.put("infoBySid",infoBySid);
        return map;
    }
}
