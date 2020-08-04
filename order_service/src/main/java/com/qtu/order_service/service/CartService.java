package com.qtu.order_service.service;

import com.qtu.order_service.entity.CartItem;
import com.qtu.util.CacheResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车业务接口
 * @author Hu Shengkai
 * @create 2020-01-09 23:59
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * @param request
     * @param goodsId 商品规格id
     * @param num
     * @return
     */
    public CacheResult addToCart(String token, HttpServletRequest request, Integer goodsId, Integer num);

    /**
     * 通过商品id得到 商品项
     * @param goodsId
     * @return
     */
    public CartItem getCartItemByGoodsId(Integer goodsId);

    /**
     * 从购物车删除商品
     * @param goodsId
     * @param request
     * @return
     */
    public CacheResult deleteFromCart(String token, HttpServletRequest request, Integer goodsId);

    /**
     * 修改商品数量
     * @param goodsId
     * @param num
     * @param request
     * @return
     */
    public CacheResult updateGoodsNum(String token, HttpServletRequest request, Integer goodsId, Integer num);

    /**
     * 得到购物车商品列表
     * @param request
     * @return
     */
    public CacheResult getCartItemList(String token ,HttpServletRequest request);

    /**
     * 商品结算页信息
     * @param request
     * @param cartItemIds
     * @return
     */
    public CacheResult checkOut(String token, HttpServletRequest request, List<String> cartItemIds);
}
