package com.qtu.product_service.service.impl;

import com.qtu.product_service.entity.ProductCountZswBean;
import com.qtu.product_service.entity.StatisticsBean;
import com.qtu.product_service.entity.Zbean;
import com.qtu.product_service.mapper.Zswmapper;
import com.qtu.product_service.service.StatisticsService;
import com.qtu.util.CookieUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private Zswmapper zswmapper;


    //业绩统计----员工管理
    @Override
    public StatisticsBean getStatistics(HttpServletRequest request, Integer token, Integer orderStep, Integer condition) {
        /* Integer token1 = Integer.parseInt(CookieUtils.getCookieValue(request, "TT_TOKEN"));*/
        String time1 = null;
        String time2 = null;
        Date date = new Date();
        if (condition == 1) {
            Map map = formatPrint(date);
            time1 = (String) map.get("time1");
            time2 = (String) map.get("time2");
        } else {
            time1 = today(date);
        }
        if (zswmapper.selectStatistics(token, orderStep, time1, time2) == null) {
            return new StatisticsBean();
        }
        return zswmapper.selectStatistics(token, orderStep, time1, time2);
    }

    //业绩统计----仓库管理
    @Override
    public StatisticsBean getCk(HttpServletRequest request, Integer token) {
        /* Integer token1 = Integer.parseInt(CookieUtils.getCookieValue(request, "TT_TOKEN"));*/
        String time1 = null;
        String time2 = null;
        Date date = new Date();
        Map map = formatPrint(date);
        time1 = (String) map.get("time1");
        time2 = (String) map.get("time2");
        zswmapper.selectCk(token, time1, time2);
        if (zswmapper.selectCk(token, time1, time2) == null) {
            return new StatisticsBean();
        }
        return zswmapper.selectCk(token, time1, time2);
    }

    //业绩管理——————客户
    @Override
    public StatisticsBean getKh(HttpServletRequest request, Integer token, Integer deleted, Integer condition) {
        String time1 = null;
        String time2 = null;
        Date date = new Date();
        Map map = formatPrint(date);
        time1 = (String) map.get("time1");
        time2 = (String) map.get("time2");
        return zswmapper.selectKh(token, time1, time2, deleted, condition);
    }

    //得出商品的销量
    @Override
    public List<ProductCountZswBean> getProductCount() {
        String time1 = null;
        String time2 = null;
        Date date = new Date();
        Map map = formatPrint(date);
        time1 = (String) map.get("time1");
        time2 = (String) map.get("time2");
        return zswmapper.selectProductCount(time1, time2);
    }

    //查询订单的详细信息（退货===正常）
    /*
     * conditionTime==1 ====查询当月
     * conditionTime==2 ====查询上一月
     * conditionTime==3 ====查询当天
     * condition==0  ===退货中
     * condition==1 ====正常
     * */
    @Override
    public List<Zbean> getProductCount2(String time3, String time4,Integer conditionTime) {
        String time1 = null;
        String time2 = null;
        /*查询当月*/
        if (time3 == null && conditionTime == 1) {
            Date date = new Date();
            Map map = formatPrint(date);
            time1 = (String) map.get("time1");
            time2 = (String) map.get("time2");
        } else if (time3 == null && conditionTime == 2) {//查询上一月
            Date date = new Date();
            Map map = formatPrinted(date);
            time1 = (String) map.get("time1");
            time2 = (String) map.get("time2");
        } else if (time3 == null && conditionTime == 3) {//获取当天时间
            Date date = new Date();
            time1 = today(date);
        }
        //合成页面需要的bean
        List<Zbean> zList = new LinkedList<>();
        List<ProductCountZswBean> productCountZswBeanList = new LinkedList<>();//退货中
        List<ProductCountZswBean> productCountZswBeanList1 = new LinkedList<>();
        if (zswmapper.selectProductCount2(time1, time2, time3, time4, 0).size() > zswmapper.selectProductCount2(time1, time2, time3, time4, 1).size()) {
            productCountZswBeanList1 = zswmapper.selectProductCount2(time1, time2, time3, time4, 0);//退货中
            productCountZswBeanList = zswmapper.selectProductCount2(time1, time2, time3, time4, 1);//正常订单
        } else {
            productCountZswBeanList = zswmapper.selectProductCount2(time1, time2, time3, time4, 0);//退货中
            productCountZswBeanList1 = zswmapper.selectProductCount2(time1, time2, time3, time4, 1);
        }
        for (int i = 0; i < productCountZswBeanList1.size(); i++) {
            Zbean zbean = new Zbean();
            for (int j = 0; j < productCountZswBeanList.size(); j++) {
                if (productCountZswBeanList1.get(i).getGoodsName().equals(productCountZswBeanList.get(j).getGoodsName())) {
                    zbean.setGoodsName(productCountZswBeanList1.get(i).getGoodsName());
                    zbean.setAmount(productCountZswBeanList1.get(i).getAmount());
                    zbean.setBuyNum(productCountZswBeanList1.get(i).getBuyNum());
                    zbean.setAmountError(productCountZswBeanList.get(j).getAmount());
                    zbean.setBuyNumError(productCountZswBeanList.get(j).getBuyNum());
                    zList.add(zbean);
                }
            }
            if (zbean.getGoodsName() == null) {
                zbean.setGoodsName(productCountZswBeanList1.get(i).getGoodsName());
                zbean.setAmount(productCountZswBeanList1.get(i).getAmount());
                zbean.setBuyNum(productCountZswBeanList1.get(i).getBuyNum());
                zList.add(zbean);
            }
        }
        return zList;
       /* if (zswmapper.selectProductCount2(time1,time2,time3,time4,condition).size()==0){
            ProductCountZswBean productCountZswBean = new ProductCountZswBean();
            List<ProductCountZswBean> list = new LinkedList<>();
            list.add(productCountZswBean);
            return list;
        }
        return zswmapper.selectProductCount2(time1,time2,time3,time4,condition);*/
    }


    //得出每月的第一天和最后一天
    private Map<String, Object> formatPrint(Date date) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        map.put("time1", sdf.format(date));
        System.out.println(sdf.format(date));
        int maxDay = calendar.getActualMaximum(Calendar.DATE);//调用方法找到本月中的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);//
        date = calendar.getTime();
        map.put("time2", sdf.format(date));
        return map;
    }

    //得出上一月的第一天和最后一天
    private Map<String, Object> formatPrinted(Date date) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//获取上个月的月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        map.put("time1", sdf.format(date));
        System.out.println(sdf.format(date));
        int maxDay = calendar.getActualMaximum(Calendar.DATE);//调用方法找到本月中的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);//
        date = calendar.getTime();
        map.put("time2", sdf.format(date));
        return map;
    }

    ///获取当天时间
    private String today(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        String time = sdf.format(date);
        return time;
    }
}
