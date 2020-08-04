package com.qtu.order_service.service.impl;

import com.qtu.order_service.bean.OrderBean;
import com.qtu.order_service.bean.ScheduleBean;
import com.qtu.order_service.mapper.OrderBeanMapper;
import com.qtu.order_service.mapper.ScheduleBeanMapper;
import com.qtu.order_service.service.OrderBeanService;
import com.qtu.order_service.service.ScheduleBeanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swl
 * @create 2020-01-10 14:42
 */
@Service
@Transactional
public class ScheduleBeanServiceImpl implements ScheduleBeanService {

    @Resource
    ScheduleBeanMapper scheduleBeanMapper;
    @Override
    public List<ScheduleBean> getOrderList(Integer warehouserId, Integer orderStep) {
        List<ScheduleBean> beanList = scheduleBeanMapper.selectByWareHouseId(warehouserId, orderStep);

        return beanList;
    }

    @Override
    public List<ScheduleBean> getGoodsList(String orderId) {
        List<ScheduleBean> goodsList = scheduleBeanMapper.selectGoodsList(orderId);
        return goodsList;
    }

    @Override
    public Map<String, Object> update(String checkId, String orderId,Integer orderStep) {
        Map<String, Object> map=new HashMap<>();
        String[] arrayA = checkId.split(";");
        ScheduleBean bean = scheduleBeanMapper.selectTest(orderId);
        Integer step = bean.getOrderStep();
        if (step==2||step==3||step==4){
            int count=0;
            for (int i=0;i<arrayA.length;i++){
                Integer value = Integer.valueOf(arrayA[i]);
                Integer integer = scheduleBeanMapper.updateGoods(value, orderId);
                if (integer>0){
                    count++;
                }
            }
            if(count!=arrayA.length){
                map.put("msg","单个商品修改出现问题");
            };
            List<ScheduleBean> list = scheduleBeanMapper.selectGoodsList(orderId);
            boolean result=false;
            for (ScheduleBean s:list){
                Integer pickingNum = s.getPickingNum();
                if(pickingNum==0){
                    result=true;
                }
            }
            if(result){
                map.put("msg","还有未配送的");
            }else{
                Integer integer = scheduleBeanMapper.updateOrderList(orderStep + 6, orderId);
                map.put("msg","配送成功");
            }
        }else{
            int count=0;
            for (int i=0;i<arrayA.length;i++){
                Integer value = Integer.valueOf(arrayA[i]);
                Integer integer = scheduleBeanMapper.updateGoodsTwo(value, orderId);
                if (integer>0){
                    count++;
                }
            }
            if(count!=arrayA.length){
                map.put("msg","单个商品修改出现问题");
            };
            List<ScheduleBean> list = scheduleBeanMapper.selectGoodsList(orderId);
            boolean result=false;
            for (ScheduleBean s:list){
                Integer examineNum = s.getExamineNum();
                if(examineNum==0){
                    result=true;
                }
            }
            if(result){
                map.put("msg","还有未验证的");
            }else{
                Integer integer = scheduleBeanMapper.updateOrderList(orderStep -3, orderId);
                map.put("msg","验证成功");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> getOneOrderInfo(String orderId,Integer orderStep) {
        Map<String,Object> map=new HashMap<>();
        ScheduleBean scheduleBean = scheduleBeanMapper.selectOrderGoods(orderId);
        map.put("bean",scheduleBean);
        List<ScheduleBean> beanList = scheduleBeanMapper.selectGoodsList(orderId);
        if (orderStep==2||orderStep==3||orderStep==4){
            List<ScheduleBean> tList=new ArrayList<>();
            List<ScheduleBean> fList=new ArrayList<>();
            for (ScheduleBean s:beanList){
                if(s.getPickingNum()==1){
                    tList.add(s);
                }else{
                    fList.add(s);
                }
            }
            map.put("over",tList);
            map.put("no",fList);
        }else if(orderStep==8||orderStep==9||orderStep==10){
            List<ScheduleBean> tList=new ArrayList<>();
            List<ScheduleBean> fList=new ArrayList<>();
            for (ScheduleBean s:beanList){
                if(s.getExamineNum()==1){
                    tList.add(s);
                }else{
                    fList.add(s);
                }
            }
            map.put("over",tList);
            map.put("no",fList);
        }else{
           map.put("over",beanList);
        }
        return map;
    }
}
