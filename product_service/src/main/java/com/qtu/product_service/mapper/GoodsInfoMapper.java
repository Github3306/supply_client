package com.qtu.product_service.mapper;

/**
 * @author swl
 * @create 2019-12-24 20:24
 */

import com.qtu.entity.Category;
import com.qtu.product_service.entity.GoodsInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsInfoMapper {

    /**
     * 查询商品信息
     * @param bname     商品品牌名称
     * @param goodsName 商品名称
     * @return
     */
    List<GoodsInfoBean> selectGoodsInfo(@Param("bname") String bname, @Param("goodsName") String goodsName);

    /**
     * 查询所有的父类目
     * @return
     */
    List<Category> selectAllPCategory();

    /**
     * 根据parentId查询子类目
     * @param parentId
     * @return
     */
    List<Category> selectChildCategory(Integer parentId);

    /**
     * 查询父类目所对应的商品信息
     * @param id
     * @return
     */
    List<GoodsInfoBean> selectGoodsInfoByCateParentId(Integer id);

    List<GoodsInfoBean> selectGoodsInfoByCateParentIds(@Param("id") Integer id,@Param("zonghe") Integer zonghe,@Param("jiage")Integer jiage,@Param("xiaoliang")Integer xiaoliang);


    List<GoodsInfoBean> selectGoodsInfoByCateParentIdss(@Param("id") Integer id,@Param("zonghe") Integer zonghe,@Param("jiage")Integer jiage,@Param("xiaoliang")Integer xiaoliang);


    /**
     * 根据商品详情id查询商品详情
     * @param sid
     * @return
     */
    GoodsInfoBean selectGoodsInfoBySid(Integer sid);

}
