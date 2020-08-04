package com.qtu.user_service.service;

import com.qtu.entity.BmAddress;
import com.qtu.util.MyResult;

/**
 * Created by IntelliJ IDEA.
 *
 * @ClassName: IMemberService
 * @author: lws
 * @create: 2020-01-09 16:51
 * @description:
 */
public interface IMemberService {

    /**
     * @Description: 通过token查找单个商户信息
     * @Param: [token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 11:12
     */
    public MyResult selectByToken(String token);

    /**
     * @Description: 通过token查找收货地址
     * @Param: [sellerId]
     * @return: java.util.List<com.qtu.entity.BmAddress>
     * @Author: lws
     * @Date: 2020/1/10 10:53
     */
    public MyResult listAddrByToken(String token);

    /**
     * @Description: 添加地址信息
     * @Param: [address]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 15:15
     */
    public MyResult insertAddress(BmAddress address);

    /**
     * @Description:  删除地址信息
     * @Param: [addId]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 21:12
     */
    public MyResult deleteAddress(Integer addId);

    /**
     * @Description: 修改登陆密码
     * @Param: [oldPassword, newPassword, token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 11:33
     */
    public MyResult changePassword(String oldPassword,String newPassword,String token);

    /**
     * @Description: 修改支付密码
     * @Param: [oldPayPassword, newPayPassword, token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 11:33
     */
    public MyResult changePayPassword(String oldPayPassword,String newPayPassword,String token);


    /**
     * @Description: 修改默认地址
     * @Param: [addId]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 14:29
     */
    public MyResult changeDefault(String token,Integer addId);

    /**
     * @Description: 得到当前选中的地址信息
     * @Param: [addId]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 11:32
     */
    public MyResult getSellerTarAddress(Integer addId);

    /**
     * @Description: 收货地址修改
     * @Param: [address]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 20:21
     */
    public MyResult editAddress(BmAddress address);

    /**
     * @Description: 上传及更新商家头像
     * @Param: [backImg, id]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/14 10:46
     */
    public MyResult updateSellerBackImg(String backImg,Integer id);

    /**
     * @Description: 通过token查询商家默认地址
     * @Param: [token]
     * @return: com.qtu.entity.BmAddress
     * @Author: lws
     * @Date: 2020/1/15 20:38
     */
    MyResult findDefaultAddress(String token);
}
