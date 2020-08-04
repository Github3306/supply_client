package com.qtu.user_service.mapper;

import com.qtu.entity.BmAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BmAddressMapper {
    int deleteByPrimaryKey(Integer addId);

    int insert(BmAddress record);

    int insertSelective(BmAddress record);

    BmAddress selectByPrimaryKey(Integer addId);

    int updateByPrimaryKeySelective(BmAddress record);

    int updateByPrimaryKey(BmAddress record);

    /**
     * @Description: 查询商家所有收货地址
     * @Param: [SellerId]
     * @return: java.util.List<com.qtu.entity.BmAddress>
     * @Author: lws
     * @Date: 2020/1/15 20:39
     */
    List<BmAddress> listBySellerId(Integer SellerId);

    /**
     * @Description: 查询商家默认收货地址
     * @Param: [token]
     * @return: com.qtu.entity.BmAddress
     * @Author: lws
     * @Date: 2020/1/15 20:39
     */
    BmAddress selectByToken(String token);

}