package com.learn.config.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author CY小鱼儿
 * @Date 2021/5/7 20:43
 * 用户登录拦截器
 **/
public class UserInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession=request.getSession();
        Object user=httpSession.getAttribute("user");
        if (user!=null){
            return true;
        }
        response.sendRedirect("/");
        return false;
    }

}
