package com.takeaway.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义拦截器
 * @author kafka
 */
@SpringBootConfiguration
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("uid")==null){
            //重定向到首页
            response.sendRedirect("login.html");
            return false;
        }
        return true;
    }
}
