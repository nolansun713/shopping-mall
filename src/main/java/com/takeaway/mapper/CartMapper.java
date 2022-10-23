package com.takeaway.mapper;

import com.takeaway.entity.Cart;
import com.takeaway.entity.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author kafka
 */
@Mapper
public interface CartMapper {
    /**
     * 添加 购物车
     * @param cart
     * @return
     */
    Integer insertCart(Cart cart);
    /**
     * 根据商品的id和用户id，查询购物车是否有该商品
     * @param uid 用户id
     * @param pid 商品id
     * @return
     */
    Cart  findByUidAndPid(Integer uid,Integer pid);

    /**
     * 购物车有该商品时，则更新数量
     * @param cid
     * @param num
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateCart(Integer cid,Integer num,String modifiedUser, Date modifiedTime);

    /**
     * 根据 uid查询用户购物车信息
     * @param uid
     * @return
     */
    List<CartVo>  findVOByUid(Integer uid);

    /**
     * 根据购物车数据id查询购物车数据详情
     * @param cid 购物车数据id
     * @return 匹配的购物车数据详情，如果没有匹配的数据则返回null
     */
    Cart findByCid(Integer cid);

    /**
     * 删除 购物车数据
     * @param cid
     * @return
     */
    Integer deleteCart(Integer cid);

    /**
     * 结算购物车
     * @param cids
     * @return
     */
    List<CartVo>findVOByCids(Integer[] cids);
}
