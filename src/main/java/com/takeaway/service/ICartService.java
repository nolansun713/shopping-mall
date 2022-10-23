package com.takeaway.service;

import com.takeaway.entity.Cart;
import com.takeaway.entity.CartVo;

import java.util.List;

/**
 * @author kafka
 */
public interface ICartService {
    /**
     * 添加购物车
     * @param uid
     * @param pid
     * @param amount
     * @param username
     */
    void addCart(Integer uid,Integer pid,Integer amount,String username);
    /**
     * 显示购物车数据
     * @param uid
     * @return
     */
    List<CartVo> getVoByUid(Integer uid);

    /**
     * 将购物车中某商品的数量加1
     * @param cid 购物车数量的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 减少购物车数量
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer DecreaseNum(Integer cid,Integer uid,String username);

    /**
     * 删除购物车数据
     * @param cid
     * @return
     */
    void DeleteCart(Integer cid);

    /**
     * 购物车结算
     * @param uid
     * @param cids
     * @return
     */
    List<CartVo> getVOByCids(Integer uid,Integer[] cids);

}
