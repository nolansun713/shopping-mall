package com.takeaway.controller;
;
import com.takeaway.entity.User;
import com.takeaway.service.IUserService;
import com.takeaway.until.DataRes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author kafka
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController  {
    @Resource
    private IUserService userService;
    /**添加用户**/
    @PostMapping("add_User")
    @ResponseBody
    public DataRes<Void> addUser(User user){
        userService.addUser(user);
        return  new DataRes<>(OK);
    }
    @PostMapping("login")
    @ResponseBody
    public DataRes<Void> login(User user, HttpSession session){
        User result= userService.login(user);
        session.setAttribute("uid",result.getUid());
        session.setAttribute("username",result.getUsername());
        return new DataRes<>(OK);
    }
    @RequestMapping("logout")
    public DataRes<Void> login(HttpSession session){
        /**清楚session,退出登录**/
        session.getAttribute("uid");
        Enumeration e=session.getAttributeNames();
        while(e.hasMoreElements()){
            String sessionName=(String)e.nextElement();
            session.removeAttribute(sessionName);
        }
        return new DataRes<>(OK);
    }
    @PostMapping("getCode")
    public DataRes<Void> getCode(String email){
        System.out.println(email);
        userService.GetCode(email);
        return new DataRes<>(OK);
    }
    @PostMapping("update_Password")
    public DataRes<Void> updatePassword(User user){
        System.out.println(user);
        userService.updatePassword(user);
        return new DataRes<>(OK);
    }
}
