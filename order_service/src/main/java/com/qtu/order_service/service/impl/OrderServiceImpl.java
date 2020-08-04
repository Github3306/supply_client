package com.qtu.order_service.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.qtu.entity.*;
import com.qtu.order_service.client.RedisFeignClient;
import com.qtu.order_service.entity.CartItem;
import com.qtu.order_service.entity.CheckOutBean;
import com.qtu.order_service.mapper.BmGoodsMapper;
import com.qtu.order_service.mapper.OrderGoodsMapper;
import com.qtu.order_service.mapper.OrderMapper;
import com.qtu.order_service.mapper.SpecificationMapper;
import com.qtu.order_service.service.CartService;
import com.qtu.order_service.service.OrderService;
import com.qtu.util.CacheResult;
import com.qtu.util.CookieUtils;
import com.qtu.util.MyResult;
import com.qtu.util.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Hu Shengkai
 * @create 2020-01-07 10:49
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisFeignClient redisFeignClient;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private BmGoodsMapper bmGoodsMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    //redis购物车商品项对应的key前缀
    private String REDIS_CART_ITEM_KEY = "CART_ITEM_KEY";


    @Transactional
    @Override
    public MyResult createOrder(String token, HttpServletRequest request , String addressId , String mark , CheckOutBean checkOutBean) {
        /*
            创建订单：
            （1）生成订单id，使用uuid生成
            （2）补全订单信息
            （3）生成订单商品表信息，插入数据库
             (4) 从购物车中移除商品
         */
        //调用服务redis服务查缓存
        CacheResult result = redisFeignClient.getFromRedis(token);
        BmMember member = (BmMember) CacheResult.linkedHashMapToObject(result.getData(), BmMember.class);
        /*if (member == null){
            //测试，先指定一个用户
            member = new BmMember();
            member.setId(1);
        }*/

        //1:生成订单号
        String orderId = snowflakeIdWorker.nextId()+"";

        //2:创建订单，补全订单信息
        BmInOrderList order = new BmInOrderList();
        order.setOrderId(orderId);
        order.setOrderState(1);//正常订单
        order.setOrderStep(1);//订单步骤为：未支付
        order.setDeliveryAddId(addressId); //订单地址
        order.setCreateTime(new Date());
        order.setMark(mark); //设置备注
        if (checkOutBean.getTotalDeliveryFee() == null) {
            //测试需要
            checkOutBean.setTotalDeliveryFee(new BigDecimal(0));
        }
        order.setDeliveryFee(checkOutBean.getTotalDeliveryFee());//邮费
        if (checkOutBean.getTotalPrice() == null) {
            //测试需要
            checkOutBean.setTotalPrice(new BigDecimal(0));
        }
        order.setAmount(checkOutBean.getTotalPrice());//总金额
        order.setSellerId(member.getId()); //
        //数据插入数据库
        orderMapper.insert(order);


        //3:循环遍历购买商品集合，生成订单商品表
        List<CartItem> cartItemList = checkOutBean.getCartItemList();
        for (CartItem cartItem : cartItemList) {
            /*
                减少库存操作：
                （1）减少缓存中的商品项库存量
                （2）同步数据库中商品项的库存量
                （3）上面的两部都在同步代码块中进行，防止并发问题
             */
            synchronized (OrderServiceImpl.class){
                String key = REDIS_CART_ITEM_KEY+":"+cartItem.getId(); //拿到组合key
                //调用redis服务获取缓存
                CartItem item = (CartItem) CacheResult.linkedHashMapToObject(
                        redisFeignClient.getFromRedis(key).getData(),CartItem.class);
                if (item == null){
                    //缓存中没有，从数据库查询
                    item = bmGoodsMapper.selectCartItemBySpecificationId(cartItem.getId());
                    //加入缓存
                    redisFeignClient.setToRedis(key,item,24*60*60l); //缓存时间为1天
                }
                if (cartItem.getGoodsNum() > item.getStock()){
                    throw new RuntimeException("商品："+cartItem.getGoodsName()+"库存不足,生成订单失败");
                }

                //（1）减少缓存中的商品项库存量
                Integer newStock = item.getStock() - cartItem.getGoodsNum();
                item.setStock(newStock);
                redisFeignClient.setToRedis(key, item, 24*60*60L);
                //（2）同步数据库中商品项的库存量
                Specification specification = new Specification();
                specification.setId(item.getId());
                specification.setStock(newStock);
                specificationMapper.updateByPrimaryKeySelective(specification);
            }
            //生成订单商品表
            BmInOrderGoodsList orderGoods = new BmInOrderGoodsList();
            orderGoods.setSpId(cartItem.getId()); //规格id
            orderGoods.setGoodsId(cartItem.getGoodsId()); //商品id
            orderGoods.setOrderId(orderId); //订单id
            orderGoods.setSname(cartItem.getGoodsName()); //规格名称
            orderGoods.setGoodsName(cartItem.getGoodsName()); //商品名称
            orderGoods.setGoodsImg(cartItem.getGoodsImgUrl()); //商品图片
            orderGoods.setBuyNum(cartItem.getGoodsNum()); //购买数量
            orderGoods.setPrice(cartItem.getPrice()); //商品价格
            orderGoods.setDeliveryNum(0);
            orderGoods.setExamineNum(0);
            orderGoods.setLoadingNum(0);
            orderGoods.setLoadingNum(0);

            //数据插入数据库
            orderGoodsMapper.insert(orderGoods);

            //从购物车中移除商品
            cartService.deleteFromCart(token, request, cartItem.getId());
        }


        return MyResult.ok(orderId);
    }
}
