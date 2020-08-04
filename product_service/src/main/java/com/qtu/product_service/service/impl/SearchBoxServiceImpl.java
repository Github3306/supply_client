package com.qtu.product_service.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.qtu.entity.BmMember;
import com.qtu.entity.SysEmployee;
import com.qtu.product_service.bean.SearchBoxBean;
import com.qtu.product_service.bean.Shop;
import com.qtu.product_service.bean.search;
import com.qtu.product_service.client.RedisFeignClient;
import com.qtu.product_service.mapper.GoodsMapper;
import com.qtu.product_service.service.SearchBoxService;
import com.qtu.util.CacheResult;
import com.qtu.util.CookieUtils;
import com.qtu.util.MyResult;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chb
 * @date 2020/1/10 - 14:47
 **/
@Service
@Transactional
public class SearchBoxServiceImpl implements SearchBoxService {

    @Resource
    private RedisFeignClient redisFeignClient;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<search> getListSearchBox(String keyWord) {
        String ppName = keyWord;
        String ggName = keyWord;

        List<search> listSearchBox = goodsMapper.seacrchBox(ppName, ggName);
        return listSearchBox;
    }

    @Override
    public PageInfo<search> getPageInfoBox(Integer pageNo, Integer pageSize,String keyWord) {
        PageHelper.startPage(pageNo,pageSize);
        List<search> listSearchBox = getListSearchBox(keyWord);
        PageInfo<search> pageInfo = new PageInfo<>(listSearchBox);
        return pageInfo;
    }

    @Override
    public List<search> getHotShopping() {
        List<search> hotShopping = goodsMapper.getHotShopping();
        return hotShopping;
    }

    @Override
    public List<search> getNewShopping() {

        List<search> newShopping = goodsMapper.getNewShopping();
        return newShopping;
    }

    @Override
    public PageInfo<search> getPageInfoHot(Integer pageNo, Integer pageSize) {
        CacheResult page_info_hot = redisFeignClient.getFromRedis("PAGE_INFO_HOT:"+pageNo);
        List<search> hotShopping = null;
        PageInfo<search> pageInfo = null;
        System.out.println("page_info_hot"+page_info_hot.getData());
        if(page_info_hot.getData()==null || page_info_hot.getData().equals("")){
            PageHelper.startPage(pageNo,pageSize);
            hotShopping =  goodsMapper.getHotShopping();
            pageInfo = new PageInfo<>(hotShopping);
            CacheResult result = redisFeignClient.setToRedis("PAGE_INFO_HOT:"+pageNo, pageInfo, (long) (30 * 60 * 1000));
        }else{
            pageInfo = (PageInfo<search>) CacheResult.linkedHashMapToObject(page_info_hot.getData(), PageInfo.class);;
        }

        return pageInfo;
    }

    @Override
    public PageInfo<search> getPageInfoNew(Integer pageNo, Integer pageSize) {
        CacheResult result = redisFeignClient.getFromRedis("PAGE_INFO_NEW:" + pageNo);
        List<search> newShopping = null;
        PageInfo<search> pageInfo = null;
        if(result.getData() == null ||result.getData().equals("")){
            PageHelper.startPage(pageNo,pageSize);
            newShopping = goodsMapper.getNewShopping();
            pageInfo = new PageInfo<>(newShopping);
            CacheResult myResult = redisFeignClient.setToRedis("PAGE_INFO_NEW:" + pageNo, pageInfo, (long) 30 * 60 * 1000);
        }else{
            pageInfo = (PageInfo<search>) CacheResult.linkedHashMapToObject(result.getData(), PageInfo.class);
        }

        return pageInfo;
    }

    @Override
    public List<search> oftenBuyList(String tel) {

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
//
//
//        CacheResult fromRedis = redisFeignClient.getFromRedis(token);
//        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(fromRedis.getData(), BmMember.class);
//        if(member != null){
//            tel = member.getTel();
//        }else{
//            tel = "15565457711";
//        }
            List<search> searches = goodsMapper.oftenBuyList(tel);
        return searches;
    }

    @Override
    public PageInfo<search> getPageInfoOften(HttpServletRequest request,Integer pageNo, Integer pageSize) {
        String tel = new String();
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");


        CacheResult fromRedis = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(fromRedis.getData(), BmMember.class);
        if(member != null){
            tel = member.getTel();
        }
        System.out.println("tel="+tel);
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<search> searchs = null;
        if(!StringUtils.isEmpty(tel)){
            List<search>  search = goodsMapper.oftenBuyList(tel);
            searchs = new PageInfo<>(search);
        }else{
            searchs = getPageInfoNew(pageNo,pageSize);
        }
        return searchs;
    }

    @Override
    public List<Shop> getSupplierShouYe() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String tt_token_employee = CookieUtils.getCookieValue(request, "TT_TOKEN_EMPLOYEE");
        List<Shop> shops = new ArrayList<>();
        if(!StringUtils.isEmpty(tt_token_employee)){
            CacheResult fromRedis = redisFeignClient.getFromRedis(tt_token_employee);
            SysEmployee employee = (SysEmployee) CacheResult.linkedHashMapToObject(fromRedis.getData(), SysEmployee.class);

            if(employee !=null ){

                String warehouseCode = employee.getWarehouseCode();
                String tel = employee.getTel();
                CacheResult fromRedis1 = redisFeignClient.getFromRedis("SUPPLIER_SHOU_YE:" + tel);
                shops = goodsMapper.supplierShouYe(warehouseCode);
            }
        }
       else{
            String warehouseCode = "W1100000001";
            shops = goodsMapper.supplierShouYe(warehouseCode);
            }
        List<Shop> list = new ArrayList<>();
       if(shops!=null){
           for(Shop s:shops){
               Shop ss = new Shop();
               String ckCuobiao = s.getCkZuobiao();
               String[] ck = ckCuobiao.split(",");
               String dpZuobiao = s.getDpZuobiao();
               String[] dp = dpZuobiao.split(",");
               double distance = getDistance(Double.parseDouble(ck[0]), Double.parseDouble(ck[1]),
                       Double.parseDouble(dp[0]), Double.parseDouble(dp[1]));
               String distance2 = String.format("%.2f", distance);
               ss.setCkName(s.getCkName());
               ss.setDpImage(s.getDpImage());
               ss.setDpLeiXing(s.getDpLeiXing());
               ss.setDpName(s.getDpName());
               ss.setDistance(Double.parseDouble(distance2));
               ss.setDpZbJd(Double.parseDouble(ck[0]));
               ss.setDpZbiaoWd(Double.parseDouble(ck[1]));
               list.add(ss);
           }
       }


        return list;
    }

    @Override
    public List<Shop> getSupplierShouYeWeiHeZuo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String tt_token_employee = CookieUtils.getCookieValue(request, "TT_TOKEN_EMPLOYEE");
        List<Shop> shops = new ArrayList<>();
        if(!StringUtils.isEmpty(tt_token_employee)){
            CacheResult fromRedis = redisFeignClient.getFromRedis(tt_token_employee);
            SysEmployee employee = (SysEmployee) CacheResult.linkedHashMapToObject(fromRedis.getData(), SysEmployee.class);

            if(employee !=null ){

                String warehouseCode = employee.getWarehouseCode();
                String tel = employee.getTel();
                CacheResult fromRedis1 = redisFeignClient.getFromRedis("SUPPLIER_SHOU_YE:" + tel);
                shops = goodsMapper.supplierShouYeWeiHeZuo(warehouseCode);
            }
        }
        else{
            String warehouseCode = "W1100000001";
            shops = goodsMapper.supplierShouYeWeiHeZuo(warehouseCode);
        }
        List<Shop> list = new ArrayList<>();

        if(shops!=null){
            for(Shop s:shops){

                Shop sss = new Shop();
                String ckCuobiao = s.getCkZuobiao();
                String[] ck = ckCuobiao.split(",");
                String dpZuobiao = s.getDpZuobiao();
                String[] dp = dpZuobiao.split(",");
                double distance = getDistance(Double.parseDouble(ck[0]), Double.parseDouble(ck[1]),
                        Double.parseDouble(dp[0]), Double.parseDouble(dp[1]));
                String distance2 = String.format("%.2f", distance);
                sss.setCkName(s.getCkName());
                sss.setDpImage(s.getDpImage());
                sss.setDpLeiXing(s.getDpLeiXing());
                sss.setDpName(s.getDpName());
                sss.setDistance(Double.parseDouble(distance2));
                sss.setDpZbJd(Double.parseDouble(ck[0]));
                sss.setDpZbiaoWd(Double.parseDouble(ck[1]));
                list.add(sss);

            }
        }


        return list;
    }

    @Override
    public Map<String,Object> updateHeZuoRelation(String dpName) {
        Integer integer = goodsMapper.updateHeZuoRelation(dpName);
        Map<String,Object> map = new HashMap<>();
        if(integer>0){
            map.put("state",200);
        }
        return map;
    }

    /**
     * 根据两坐标的经纬度计算距离
     * @param longitudeFrom
     * @param latitudeFrom
     * @param longitudeTo
     * @param latitudeTo
     * @return
     */
    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);

        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }
}
