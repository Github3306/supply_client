package com.qtu.order_service.mapper;

import com.qtu.order_service.bean.OrderBean;
import com.qtu.order_service.bean.ScheduleBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swl
 * @create 2020-01-10 11:39
 */
@Mapper
public interface ScheduleBeanMapper {
    //获取当前登陆着的订单
    public List<ScheduleBean> selectByWareHouseId(@Param("warehouserId") Integer warehouserId,@Param("orderStep") Integer orderStep);

    //根据orderId查询订单所有商品信息
    public List<ScheduleBean> selectGoodsList(String orderId);

    //根据orderId查询订单信息
    public ScheduleBean selectOrderGoods(String orderId);

    //修改配货
    public Integer updateGoods(@Param("goodsId") Integer goodsId,@Param("orderId") String orderId);

    //修改验货
    public Integer updateGoodsTwo(@Param("goodsId") Integer goodsId,@Param("orderId") String orderId);

    //修改订单状态
    public Integer updateOrderList(@Param("orderStep") Integer orderStep,@Param("orderId") String orderId);

    //测试查询状态
    public ScheduleBean selectTest(String orderId);
}
