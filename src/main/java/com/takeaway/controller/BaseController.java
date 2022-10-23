package com.takeaway.controller;

import com.takeaway.service.ex.*;
import com.takeaway.until.DataRes;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层基类
 * @author kafka
 */
public class BaseController {
    public static final int OK=200;
    @ExceptionHandler(ServiceException.class) /***用于统一处理异常**/
    public DataRes<Void> handleException(Throwable e){
        DataRes<Void> result=new DataRes(e);
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
            result.setMessage("用户名已被占用");
        }else if(e instanceof InsertException) {
            result.setMessage("注册异常");
            result.setState(5000);
        }else if(e instanceof UserNotFoundException) {
            result.setMessage("用户名不存在");
            result.setState(6001);
        }else if(e instanceof PasswordNotMathException) {
            result.setMessage("密码错误");
            result.setState(6002);
        }else if(e instanceof AddressCountLimitException) {
            result.setMessage("收货地址添加超过限制");
            result.setState(7001);
        }else if(e instanceof AccessDeniedException) {
            result.setMessage("非法访问");
            result.setState(7002);
        }else if(e instanceof AddressNotFoundException) {
            result.setMessage("收货地址不存在");
            result.setState(7003);
        }else if(e instanceof UpdateException) {
            result.setMessage("更新失败");
            result.setState(5001);
        }else if(e instanceof DeleteException) {
            result.setMessage("更新失败");
            result.setState(8000);
        }else if(e instanceof CartNotFoundException) {
            result.setMessage("购物车数据不存在");
            result.setState(7004);
        }else if(e instanceof EmailFormatException) {
            result.setMessage("邮箱格式不符合要求");
            result.setState(6003);
        }else if(e instanceof TimeOutException) {
            result.setMessage("验证码超时无效");
            result.setState(6004);
        }else if(e instanceof CoolingTimeException) {
            result.setMessage("验证码冷却");
            result.setState(6005);
        }else if(e instanceof CodeFalseException) {
            result.setMessage("验证码错误");
            result.setState(6006);
        }else if(e instanceof EmailBoxAndUsernameException){
            result.setMessage("邮箱和账号不一致");
            result.setState(6007);
        }else if(e instanceof EmailSoleException){
            result.setMessage("邮箱已被注册");
            result.setState(6008);
        }
        return result;
    }

    /**
     * 获取 session 中的uid
     * @param session
     * @return
     */
    protected final Integer getUidSession(HttpSession session){
        session.getAttribute("uid");
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final String  getUsernameSession(HttpSession session){
        session.getAttribute("username");
        return session.getAttribute("username").toString();
    }
}
