package com.qtu.product_service.mapper;

import com.qtu.product_service.entity.LogisticsBean;
import com.qtu.product_service.entity.WuliuBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张显
 * @create 2020-01-14 11:04
 */
@Mapper
public interface LogisticsMapper {

    /**
     * 根据状态码查询状态所对应的物流信息
     * @param statusCode   物流状态    1：装货    2：交货
     * @param logisticsId  物流人id
     * @return
     */
    List<LogisticsBean> selectLogisticsStatus(@Param("statusCode") Integer statusCode,
                                              @Param("logisticsId") Integer logisticsId);

    /**
     * 根据订单id查询订单的物流信息
     * @param orderId
     * @return
     */
    List<WuliuBean> selectLogisticsInfoByOrderId(String orderId);

    /**
     * 更新装货物流状态
     * @param logisticsId   物流人员id
     * @param orderId       订单id
     * @param spId          商品详情id
     * @return
     */
    int updateZhuangHuoStatus(@Param("logisticsId") Integer logisticsId,@Param("orderId") String orderId,
                              @Param("spId") Integer spId);

    /**
     * 更新交货物流状态
     * @param logisticsId
     * @param orderId
     * @param spId
     * @return
     */
    int updateJiaoHuoStatus(@Param("logisticsId") Integer logisticsId,@Param("orderId") String orderId,
                            @Param("spId") Integer spId);

    /**
     * 更新物流转台码
     * @param logisticsId
     * @param orderId
     * @param orderStep   状态码
     * @return
     */
    int updateStepCode(@Param("logisticsId") Integer logisticsId,
                            @Param("orderId") String orderId,@Param("orderStep") Integer orderStep);
}
