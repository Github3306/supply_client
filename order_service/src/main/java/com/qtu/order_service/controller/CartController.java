package com.qtu.order_service.controller;

import com.qtu.order_service.service.CartService;
import com.qtu.util.CacheResult;
import com.qtu.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hu Shengkai
 * @create 2020-01-10 9:11
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    private static  final String MEMBER_TOKEN = "MEMBER_TOKEN:";
    /**
     * 添加商品到购物车
     * @param goodsId 商品规格id
     * @param num 商品数量
     * @param request
     * @return
     */
    @RequestMapping("/add/{goodsId}")
    public CacheResult addToCart(String token, @PathVariable("goodsId") Integer goodsId, @RequestParam(defaultValue = "1") Integer num,
                                 HttpServletRequest request){
        CacheResult result = null;
        try {
            result = cartService.addToCart(MEMBER_TOKEN+token, request, goodsId, num);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 得到购物车商品集合
     * @param request
     * @return
     */
    @RequestMapping("/map")
    public CacheResult getCartItemMap(String token ,HttpServletRequest request) {
        CacheResult result = null;
        try {
            result = cartService.getCartItemList(MEMBER_TOKEN+token,request);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 从购物车中删除
     * @param request
     * @param goodsId
     * @return
     */
    @RequestMapping("/delete")
    public CacheResult deleteFromCart(@RequestParam("token") String token,
                                      HttpServletRequest request,
                                      @RequestParam("goodsId") Integer goodsId) {
        CacheResult result = null;
        try {
            result = cartService.deleteFromCart(MEMBER_TOKEN+token, request, goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 修改购物车中的商品数量
     * @param request
     * @param goodsId 商品id
     * @param num 商品数量
     * @return
     */
    @RequestMapping("/update")
    public CacheResult updateCartItemNum(@RequestParam("token") String token, HttpServletRequest request, Integer goodsId, Integer num) {
        CacheResult result = null;
        try {
            result = cartService.updateGoodsNum(MEMBER_TOKEN+token, request, goodsId, num);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    /**
     * 前往结算
     * @param request
     * @param cartItemIds 购物车商品项id
     * @return
     */
    @RequestMapping(value = "/goCheckOut", method = RequestMethod.POST)
    public CacheResult goCheckOut(@RequestParam("token") String token, HttpServletRequest request, String cartItemIds) {
        String[] split = cartItemIds.split(",");
        List<String> ids = Arrays.asList(split);
        CacheResult result = null;
        try {
            result = cartService.checkOut(MEMBER_TOKEN+token, request, ids);
        } catch (Exception e) {
            e.printStackTrace();
            result = CacheResult.build(400, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }
}
