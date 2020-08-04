package com.qtu.sendorders_service.mapper;

import com.qtu.entity.BmInOrderList;
import com.qtu.entity.SysEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hu Shengkai
 * @create 2020-01-13 20:04
 */
@Mapper
public interface OrderMapper {
    int updateByPrimaryKeySelective(BmInOrderList record);

    /**
     * 通过订单id 查询出购买商品的仓库
     *
     * @param orderId
     * @return
     */
    String selectWarehouseCodeByOrderId(String orderId);

    /**
     * 通过仓库id查询到仓库下的配货人员、物流人员
     *
     * @param warehouseCode
     * @param positionId
     * @return
     */
    List<Integer> selectEmployeeByWarehouseCodeAndPositionId(@Param("warehouseCode") String warehouseCode, @Param("positionId") Integer positionId);
}
