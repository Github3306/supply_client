package com.qtu.user_service.controller;

import com.qtu.entity.BmAddress;
import com.qtu.user_service.service.IMemberService;
import com.qtu.util.MyResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @ClassName: MemberController
 * @author: lws
 * @create: 2020-01-09 17:27
 * @description:
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private IMemberService memberService;

    /**
     * @Description: 通过token获取单个商家信息
     * @Param: [token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 8:55
     */
    @GetMapping("/getInfo")
    public Object selectBytoken(String token){

        return memberService.selectByToken(token).getData();
    }

    /**
     * @Description: 通过token查找收获地址
     * @Param: [sellerId]
     * @return: java.util.List<com.qtu.entity.BmAddress>
     * @Author: lws
     * @Date: 2020/1/10 10:53
     */
    @GetMapping("/listAddress")
    public Object listAddrById(String token){

        return memberService.listAddrByToken(token);
    }

    /**
     * @Description: 添加地址信息
     * @Param: [address]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 15:17
     */
    @PostMapping(value = "/insertAddress")
    public MyResult insertAddress(BmAddress address){
        address.setIsDeleted(0);
        System.out.println(address.getAddress());
        System.out.println(address.getMainAddress());
        return memberService.insertAddress(address);
    }

    /**
     * @Description: 根据id删除地址
     * @Param: [addId]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/10 20:05
     */
    @PostMapping(value = "/deleteAddress")
    public MyResult deleteAddress(Integer addId){

        return memberService.deleteAddress(addId);
    }

    /**
     * @Description: 修改登陆密码
     * @Param: [oldPassword, newPassword, token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/11 9:19
     */
    @PostMapping("/changePassword")
    public MyResult changePassword(String oldPassword,String newPassword,String token){

        return memberService.changePassword(oldPassword,newPassword,token);
    }

    /**
     * @Description: 修改支付密码
     * @Param: [oldPayPassword, newPayPassword, token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/11 9:31
     */
    @PostMapping("/changePayPassword")
    public MyResult changePayPassword(String oldPayPassword,String newPayPassword,String token){

        return memberService.changePayPassword(oldPayPassword,newPayPassword,token);
    }

    /**
     * @Description: 更改默认地址
     * @Param: [token, addId]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 10:14
     */
    @PostMapping("/changeDefaultAddr")
    public MyResult changeDefaultAddr(String token,Integer addId){

        return memberService.changeDefault(token,addId);
    }

    /**
     * @Description: 得到当前选中的地址信息，用于修改页面
     * @Param: [addId]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 11:35
     */
    @GetMapping("/getSellerTarAddress")
    public MyResult getSellerTarAddress(Integer addId){

        return memberService.getSellerTarAddress(addId);
    }

    /**
     * @Description: 修改地址
     * @Param: [address]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/13 12:50
     */
    @PostMapping("/editAddress")
    public MyResult editAddress(BmAddress address){

        return memberService.editAddress(address);
    }

    /**
     * @Description: 查找商家默认收货地址
     * @Param: [token]
     * @return: com.qtu.util.MyResult
     * @Author: lws
     * @Date: 2020/1/15 20:42
     */
    @GetMapping("/findDefaultAddr")
    public MyResult findDefaultAddress(String token){

        return memberService.findDefaultAddress(token);
    }

}
