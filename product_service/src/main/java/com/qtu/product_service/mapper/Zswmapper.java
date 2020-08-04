package com.qtu.product_service.mapper;

import com.qtu.entity.SysMechanism;
import com.qtu.product_service.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张的帅
 * @create 2019-10-23 19:52
 */
@Mapper
public interface Zswmapper {

    SysMechanism selectByPrimaryKey(Integer id);

    //查询热销商品
    List<HotProduct> selectHotProduct();

    //查询热销商品的详细信息
    HotProductInfoBean selectHotProductInfo(@Param("id") Integer id);

    //查询商品信息
    List<ProductInfoBean> selectProductInfo();

    //查询新品
    List<HotProduct> selectNewProduct();

    //查询商品品牌
    List<BrandBean>selectCategory();

    //根据品牌id去查询相应的商品
    List<ProductInfoBean> selectInfoByCategory(@Param("id") Integer id);

    //统计
    StatisticsBean selectStatistics(@Param("token") Integer token, @Param("orderStep") Integer orderStep,
                                          @Param("time1") String time1, @Param("time2")String time2);

     //业绩统计----仓库统计
    StatisticsBean selectCk(@Param("token") Integer token,@Param("time1") String time1, @Param("time2")String time2);

    //业绩统计----客户统计
    StatisticsBean selectKh(@Param("token") Integer token,@Param("time1") String time1, @Param("time2")String time2,
                            @Param("deleted") Integer deleted,@Param("condition") Integer condition);

    //业绩统计----销量统计
    List<ProductCountZswBean> selectProductCount(@Param("time1")String time1,@Param("time2")String time2);

    //商品的订单详细信息
    List<ProductCountZswBean> selectProductCount2(@Param("time1")String time1,@Param("time2")String time2,
                                                  @Param("time3")String time3,@Param("time4")String time4,
                                                  @Param("condition")Integer condition);
}
