package com.takeaway.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 *拦截器配置
 * @author kafka
 */
@SpringBootConfiguration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    /**拦截路径**/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor handlerInterceptor=new LoginInterceptor();
        //创建白名单
        List<String> list=new ArrayList<>();
        list.add("/");
        list.add("/login.html");
        list.add("/assets/**");
        list.add("/register.html");
        list.add("/index.html");
        list.add("/single-product.html");
        list.add("/shop.html");
        list.add("/updatepassword.html");
        list.add("/user/add_User");
        list.add("/user/login");
        list.add("/products/**");
        list.add("/user/**");
        /**
         * 添加拦截
         *addPathPatterns("/**")拦截路径
         * excludePathPatterns(list);放行
         */
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
}
