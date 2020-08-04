package com.qtu.order_service.service.impl;

import com.qtu.entity.BmGoods;
import com.qtu.entity.BmMember;
import com.qtu.order_service.client.RedisFeignClient;
import com.qtu.order_service.entity.BuyerCart;
import com.qtu.order_service.entity.CartItem;
import com.qtu.order_service.entity.CheckOutBean;
import com.qtu.order_service.mapper.BmGoodsMapper;
import com.qtu.order_service.service.CartService;
import com.qtu.redis_service.util.RedisUtil;
import com.qtu.util.CacheResult;
import com.qtu.util.CookieUtils;
import com.qtu.util.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 购物车业务层
 * @author Hu Shengkai
 * @create 2020-01-09 23:59
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private BmGoodsMapper bmGoodsMapper;
    @Autowired
    private RedisFeignClient redisFeignClient;

    //redis中购物车对应的key前缀
    private String REDIS_BUYER_CART_KEY = "BUYER_CART_KEY";

    //redis购物车商品项对应的key前缀
    private String REDIS_CART_ITEM_KEY = "CART_ITEM_KEY";

    @Override
    public CacheResult addToCart(String token, HttpServletRequest request, Integer goodsId, Integer num) {
        /*
            1.拿到当前商品项
            2.从redis中拿购物车,需要知道当前登录者是谁
            3.添加商品到购物车
         */
        //拿到当前商品项
        CartItem cartItem = getCartItemByGoodsId(goodsId);
        //调用服务redis服务查缓存
        CacheResult result = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(result.getData(),BmMember.class);
        /*if (member == null){
            //测试，先指定一个用户
            member = new BmMember();
            member.setId(1);
        }*/
        //添加商品到购物车
        String cartKey = REDIS_BUYER_CART_KEY+":"+member.getId(); //得到组合后的key
        result = redisFeignClient.getFromRedis(cartKey);
        BuyerCart buyerCart = (BuyerCart) CacheResult.linkedHashMapToObject(result.getData(), BuyerCart.class);
        if (buyerCart == null){
            buyerCart = new BuyerCart();
        }
        buyerCart.addToCart(cartItem, num); //添加商品进购物车
        redisFeignClient.setToRedis(cartKey, buyerCart, new Long(360*60*60*24)); //更新购物车缓存,缓存时间为一年

        return CacheResult.ok("添加购物车成功");
    }

    @Override
    public CartItem getCartItemByGoodsId(Integer goodsId) {
        String key = REDIS_CART_ITEM_KEY+":"+goodsId; //拿到组合key
        //调用redis服务获取缓存
        CartItem cartItem = (CartItem) CacheResult.linkedHashMapToObject(
                redisFeignClient.getFromRedis(key).getData(),CartItem.class);
        if (cartItem == null){
            //缓存中没有，从数据库查询
            cartItem = bmGoodsMapper.selectCartItemBySpecificationId(goodsId);
            //加入缓存
            redisFeignClient.setToRedis(key,cartItem,24*60*60l); //缓存时间为1天
        }
        return cartItem;
    }

    @Override
    public CacheResult deleteFromCart(String token, HttpServletRequest request, Integer goodsId) {
        /*
            1.从redis中拿购物车,需要知道当前登录者是谁
            2.从购物车种删除商品
         */
        //调用服务redis服务查缓存
        CacheResult result = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(result.getData(), BmMember.class);
        /*if (member == null){
            //测试，先指定一个用户
            member = new BmMember();
            member.setId(1);
        }*/
        //从购物车删除商品
        String cartKey = REDIS_BUYER_CART_KEY+":"+member.getId(); //得到组合后的key
        result = redisFeignClient.getFromRedis(cartKey);
        BuyerCart buyerCart = (BuyerCart) CacheResult.linkedHashMapToObject(result.getData(), BuyerCart.class);
        if (buyerCart != null){
            //删除
            buyerCart.deleteFromCart(goodsId);
            redisFeignClient.setToRedis(cartKey, buyerCart, new Long(360*60*60*24)); //更新购物车缓存,缓存时间为一年
        }
        return CacheResult.ok("删除商品成功");
    }

    @Override
    public CacheResult updateGoodsNum(String token, HttpServletRequest request, Integer goodsId, Integer num) {
        /*
            1.从redis中拿购物车,需要知道当前登录者是谁
            2.从购物车拿到商品，修改数量，然后更新
         */
        //调用服务redis服务查缓存
        CacheResult result = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(result.getData(), BmMember.class);
        /*if (member == null){
            //测试，先指定一个用户
            member = new BmMember();
            member.setId(1);
        }*/
        String cartKey = REDIS_BUYER_CART_KEY+":"+member.getId(); //得到组合后的key
        result = redisFeignClient.getFromRedis(cartKey);
        BuyerCart buyerCart = (BuyerCart) CacheResult.linkedHashMapToObject(result.getData(), BuyerCart.class); //拿到购物车
        if (buyerCart != null){
            //修改
            buyerCart.updateGoodsNum(goodsId, num);
            redisFeignClient.setToRedis(cartKey, buyerCart, new Long(360*60*60*24)); //更新购物车缓存,缓存时间为一年
        }
        return CacheResult.ok("商品数量修改成功");
    }

    @Override
    public CacheResult getCartItemList(String token , HttpServletRequest request) {
        //调用服务redis服务查缓存
        CacheResult result = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(result.getData(), BmMember.class); ;
       /* if (member == null){
            //测试，先指定一个用户
            member = new BmMember();
            member.setId(1);
        }*/
        String cartKey = REDIS_BUYER_CART_KEY+":"+member.getId(); //得到组合后的key
        result = redisFeignClient.getFromRedis(cartKey);
        BuyerCart buyerCart = (BuyerCart) CacheResult.linkedHashMapToObject(result.getData(), BuyerCart.class); //拿到购物车
        return CacheResult.ok(buyerCart.getCartMap());
    }

    @Override
    public CacheResult checkOut(String token, HttpServletRequest request, List<String> cartItemIds) {
        /*
            结算：
            1：得到当前登录人
            2：得到购物车
            3：循环遍历，从购物车中找商品
            4：计算商品价格
         */
        //1:
        //调用服务redis服务查缓存
        CacheResult result = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(result.getData(), BmMember.class);
        /*if (member == null){
            //测试，先指定一个用户
            member = new BmMember();
            member.setId(1);
        }*/
        //2:
        //取出购物车
        String cartKey = REDIS_BUYER_CART_KEY+":"+member.getId(); //得到组合后的key
        result = redisFeignClient.getFromRedis(cartKey);
        BuyerCart buyerCart = (BuyerCart) CacheResult.linkedHashMapToObject(result.getData(), BuyerCart.class); //拿到购物车
        CheckOutBean checkOutBean = new CheckOutBean();
        if (buyerCart != null){
            Map<String, CartItem> cartMap = buyerCart.getCartMap();
            //3:循环遍历，从购物车中找商品，计算价格
            BigDecimal totalPrice = new BigDecimal(0);
            BigDecimal totalDeliveryFee = new BigDecimal(0);
            List<CartItem> cartItemList = new ArrayList<>();
            for (String cid : cartItemIds) {
                CartItem cartItem = cartMap.get(cid.toString());
                if (cartItem == null){
                    //购物车中未找到指定商品
                    throw new RuntimeException("购物车中未找到指定商品,id为："+cid);
                }
                cartItemList.add(cartItem); //把购买的商品项信息设置到bean中
                BigDecimal price = cartItem.getPrice();
                price = price.multiply(new BigDecimal(cartItem.getGoodsNum()));
                //计算运费
                if (member.getDeliveryFee() != null){
                    totalDeliveryFee = totalDeliveryFee.add(member.getDeliveryFee());
                }
                totalPrice = totalPrice.add(totalDeliveryFee);
                totalPrice = totalPrice.add(price);
            }
            checkOutBean.setTotalPrice(totalPrice);
            checkOutBean.setTotalDeliveryFee(totalDeliveryFee);
            checkOutBean.setCartItemList(cartItemList);
        }

        return CacheResult.ok(checkOutBean);
    }
}
