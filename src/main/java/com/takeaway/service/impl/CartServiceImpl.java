package com.takeaway.service.impl;

import com.takeaway.entity.Cart;
import com.takeaway.entity.CartVo;
import com.takeaway.entity.Product;
import com.takeaway.mapper.UserSide.CartMapper;
import com.takeaway.mapper.UserSide.ProductMapper;
import com.takeaway.service.ICartService;
import com.takeaway.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author kafka
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    /**
     * 添加购物车
     */
    @Override
    public void addCart(Integer uid,Integer pid,Integer amount,String username) {
       Cart result = cartMapper.findByUidAndPid(uid,pid);
      if(result==null){
          // 查询购物车没有该商品，插入商品
          Cart cart=new Cart();
          cart.setUid(uid);
          cart.setPid(pid);
          cart.setNum(amount);
          cart.setCreatedTime(new Date());
          cart.setCreatedUser(username);
          Product product=productMapper.findPid(pid);
          cart.setPrice(product.getPrice());
          Integer row =cartMapper.insertCart(cart);
          if(row!=1){
              throw new InsertException("加入购物车异常");
          }
      }else{
          //购物车又该商品，则更新数量
          Integer cid= result.getCid();
          Integer num=result.getNum()+amount;
          Integer row=cartMapper.updateCart(cid,num,username,new Date());
          if(row!=1){
              throw new UpdateException("更新购物车异常");
          }
      }
    }

    /**
     * 显示购物车数据
     *
     * @param uid
     * @return
     */
    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        List<CartVo>  list=cartMapper.findVOByUid(uid);
        return list;
    }

    /**
     * 将购物车中某商品的数量加1
     *
     * @param cid      购物车数量的id
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() + 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateCart(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        return num;
    }

    /**
     * 减少购物车数量
     *
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    @Override
    public Integer DecreaseNum(Integer cid, Integer uid, String username) {
        Cart result=cartMapper.findByCid(cid);
        //判断数据存在
        if(result==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        //判断操做数据是否异常
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据访问非法异常");
        }
        Integer num=result.getNum();
        if(result.getNum()>1){
            num=result.getNum()-1;
        }
        Integer rows=cartMapper.updateCart(cid,num,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新购物车异常");
        }
        return num;
    }

    /**
     * 删除购物车数据
     *
     * @param cid
     * @return
     */
    @Override
    public void DeleteCart(Integer cid) {
        Integer row=cartMapper.deleteCart(cid);
        if(row!=1){
            throw new DeleteException("删除失败");
        }
    }

    /**
     * 购物车结算
     *
     * @param uid
     * @param cids
     * @return
     */
    @Override
    public List<CartVo> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVo> list=cartMapper.findVOByCids(cids);
        Iterator<CartVo> it = list.iterator();
        while (it.hasNext()) {
            CartVo cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }
}
