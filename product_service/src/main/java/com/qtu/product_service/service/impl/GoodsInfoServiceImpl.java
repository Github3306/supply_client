package com.qtu.product_service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtu.entity.Category;
import com.qtu.product_service.client.RedisFeignClient;
import com.qtu.product_service.entity.GoodsInfoBean;
import com.qtu.product_service.mapper.GoodsInfoMapper;
import com.qtu.product_service.service.GoodsInfoService;
import com.qtu.util.CacheResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张显
 * @create 2020-01-10 9:52
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private RedisFeignClient zRedisFeignClient;//注入缓存服务

    /**
     * 得到查询商品信息
     * @param pageNo
     * @param pageNum
     * @param bname
     * @param goodsName
     * @return
     */
    @Override
    public PageInfo getSelectGoodsInfo(Integer pageNo, Integer pageNum, String bname, String goodsName) {
        PageHelper.startPage(pageNo,pageNum);
        List<GoodsInfoBean> goodsInfoList = goodsInfoMapper.selectGoodsInfo(bname, goodsName);
        PageInfo<GoodsInfoBean> pageInfo=new PageInfo<>(goodsInfoList);
        return pageInfo;
    }

    /**
     * 得到所有的父类目名称
     * @return
     */
    @Override
    public List<Category> getSelectAllPCategoryInfo() {
        /*查询redis缓存*/
        CacheResult p_category = zRedisFeignClient.getFromRedis("GOODS_PARENT_CATEGORY");
        if(p_category.getData()!=null){
            List<Category> pCategoryList= (List<Category>) p_category.getData();
            return pCategoryList;
        }
        List<Category> pCategoryList = goodsInfoMapper.selectAllPCategory();
        /*将内容存储到reids中*/
        zRedisFeignClient.setToRedis("GOODS_PARENT_CATEGORY", pCategoryList, 60 * 60L);
        return pCategoryList;
    }

    /**
     * 得到parentId下的所有子类目
     * @param
     * @return
     */
    @Override
    public List<Category> getAllChildCategoryByPId(Integer categoryId) {
        /*查询redis缓存*/
        CacheResult c_category = zRedisFeignClient.getFromRedis("GOODS_CHILD_CATEGORY:"+categoryId);
        if(c_category.getData()!=null){
            List<Category> cCategoryList= (List<Category>) c_category.getData();
            return cCategoryList;
        }
        List<Category> cCategoryList = goodsInfoMapper.selectChildCategory(categoryId);
        /*将内容存储到reids中*/
        zRedisFeignClient.setToRedis("GOODS_CHILD_CATEGORY:"+categoryId,cCategoryList,60*60L);
        return cCategoryList;
    }

    /**
     * 得到父类目所对应的商品信息
     * @param id
     * @return
     */
    @Override
    public PageInfo getSelectGoodsInfoByCateId(Integer pageNo, Integer pageNum,Integer id) {
        /*查询redis缓存*/
        CacheResult allInfo = zRedisFeignClient.getFromRedis("GOODS_All_INFO");
//        if(allInfo.getData()!=null){
//            PageInfo<GoodsInfoBean> info = (PageInfo<GoodsInfoBean>) CacheResult.linkedHashMapToObject(allInfo.getData(), PageInfo.class);
//            return info;
//        }
        PageHelper.startPage(pageNo,pageNum);
        List<GoodsInfoBean> goodsInfo = goodsInfoMapper.selectGoodsInfoByCateParentId(id);
        PageInfo<GoodsInfoBean> info=new PageInfo<>(goodsInfo);
        /*将数据存到redis缓存中*/
//        zRedisFeignClient.setToRedis("GOODS_All_INFO",info,60*60L);
        return info;
    }

    /**
     * 测试
     * @param pageNo
     * @param pageNum
     * @param id
     * @param zonghe
     * @param jiage
     * @param xiaoliang
     * @return
     */
    @Override
    public PageInfo getSelectGoodsInfoByCateIds(Integer pageNo, Integer pageNum, Integer id, Integer zonghe, Integer jiage, Integer xiaoliang) {
        /*查询redis缓存*/
//        CacheResult allInfo = zRedisFeignClient.getFromRedis("GOODS_All_INFO");
//        if(allInfo.getData()!=null){
//            PageInfo<GoodsInfoBean> infos = (PageInfo<GoodsInfoBean>) CacheResult.linkedHashMapToObject(allInfo.getData(), PageInfo.class);
//            return infos;
//        }
        PageHelper.startPage(pageNo,pageNum);
        List<GoodsInfoBean> list = goodsInfoMapper.selectGoodsInfoByCateParentIds(id, zonghe, jiage, xiaoliang);
        PageInfo<GoodsInfoBean> info=new PageInfo<>(list);
//        zRedisFeignClient.setToRedis("GOODS_All_INFO",info,60*60L);
        return info;
    }

    @Override
    public PageInfo getSelectGoodsInfoByCateIdss(Integer pageNo, Integer pageNum, Integer id, Integer zonghe, Integer jiage, Integer xiaoliang) {
        /*查询redis缓存*/
//        CacheResult allInfo = zRedisFeignClient.getFromRedis("GOODS_All_INFO");
//        if(allInfo.getData()!=null){
//            PageInfo<GoodsInfoBean> infos = (PageInfo<GoodsInfoBean>) CacheResult.linkedHashMapToObject(allInfo.getData(), PageInfo.class);
//            return infos;
//        }
        PageHelper.startPage(pageNo,pageNum);
        List<GoodsInfoBean> list = goodsInfoMapper.selectGoodsInfoByCateParentIdss(id, zonghe, jiage, xiaoliang);
        PageInfo<GoodsInfoBean> info=new PageInfo<>(list);
//        zRedisFeignClient.setToRedis("GOODS_All_INFO",info,60*60L);
        return info;
    }

    /**
     * 根据商品详情id得到商品详情信息
     * @param sid
     * @return
     */
    @Override
    public GoodsInfoBean getSelectGoodsInfoBySid(Integer sid) {
        /*查询redis缓存*/
        CacheResult goods_details = zRedisFeignClient.getFromRedis("GOODS_DETAILS:"+sid);
        if(goods_details.getData()!=null){
            GoodsInfoBean infoBean = (GoodsInfoBean) CacheResult.linkedHashMapToObject(goods_details.getData(), GoodsInfoBean.class);
            return infoBean;
        }
        GoodsInfoBean info = goodsInfoMapper.selectGoodsInfoBySid(sid);
        /*将内容存储到reids中*/
        CacheResult setToRedis = zRedisFeignClient.setToRedis("GOODS_DETAILS:"+sid, info, 60 * 60L);
        return info;
    }


}
