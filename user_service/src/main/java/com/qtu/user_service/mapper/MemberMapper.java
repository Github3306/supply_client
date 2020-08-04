package com.qtu.user_service.mapper;

import com.qtu.entity.BmAddress;
import com.qtu.entity.BmMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BmMember record);

    int insertSelective(BmMember record);

    BmMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BmMember record);

    int updateByPrimaryKey(BmMember record);

    /**
     * @Description: 通过token查找商户信息
     * @Param: [token]
     * @return: com.qtu.entity.BmMember
     * @Author: lws
     * @Date: 2020/1/9 17:21
     */
    BmMember selectByToken(String token);

    /**
     * @Description: 通过token查找收货地址
     * @Param: [sellerId]
     * @return: java.util.List<com.qtu.entity.BmAddress>
     * @Author: lws
     * @Date: 2020/1/10 10:53
     */
    List<BmAddress> listAddrByToke(String token);

    /**
     * @Description: 更改登陆密码
     * @Param: [newPassword, token]
     * @return: int
     * @Author: lws
     * @Date: 2020/1/14 10:41
     */
    int changePassword(@Param("newPassword") String newPassword,@Param("token") String token);

    /**
     * @Description: 更改支付密码
     * @Param: [newPayPassword, token]
     * @return: int
     * @Author: lws
     * @Date: 2020/1/14 10:41
     */
    int changePayPassword(@Param("newPayPassword") String newPayPassword,@Param("token") String token);

    /**
     * @Description: 上传更改商家头像
     * @Param: [backImg, id]
     * @return: int
     * @Author: lws
     * @Date: 2020/1/14 10:41
     */
    int updateBackImg(@Param("backImg") String backImg,@Param("id") Integer id);


}