package com.takeaway.controller.User;

import com.takeaway.controller.BaseController;
import com.takeaway.entity.CartVo;
import com.takeaway.service.ICartService;
import com.takeaway.until.DataRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kafka
 */
@RestController
@RequestMapping("cart")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;
    @RequestMapping("add_to_cart")
    public DataRes<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        System.out.println(pid);
        Integer uid=getUidSession(session);
        String username=getUsernameSession(session);
        cartService.addCart(uid,pid,amount,username);
        return new DataRes<>(OK);
    }
    @RequestMapping({"","/"})
    public DataRes<List<CartVo>> getVoByUid(HttpSession session){
        List<CartVo> data=cartService.getVoByUid(getUidSession(session));
        return new DataRes<>(OK,data);
    }

    @PostMapping ("{cid}/num/add")
    public DataRes<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        System.out.println(cid);
        Integer uid = getUidSession(session);
        String username = getUsernameSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        System.out.println(data);
        // 返回成功
        return new DataRes<>(OK, data);
    }
    @PostMapping ("{cid}/num/decrease")
    public DataRes<Integer> DecreaseNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer uid=getUidSession(session);
        String username=getUsernameSession(session);
        Integer data=cartService.DecreaseNum(cid,uid,username);
        return new DataRes<>(OK,data);
    }
    /**删除购物车数据**/
    @PostMapping("{cid}/deleteCart")
    public DataRes<Void> deleteCart(@PathVariable("cid") Integer cid){
      cartService.DeleteCart(cid);
      return new DataRes<>(OK);
    }

    /**
     * 结算
     * @param cids
     * @param session
     * @return
     */
    @GetMapping("cartList")
    public DataRes<List<CartVo>> getVOByCids(Integer[] cids,HttpSession session){
        List<CartVo>  data= cartService.getVOByCids(getUidSession(session),cids);
        System.out.println(data);
        return  new DataRes<>(OK,data);
    }
}
