package com.learn.config.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author CY小鱼儿
 * @Date 2021/5/8 15:34
 **/
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession=request.getSession();
        Object admin=httpSession.getAttribute("admin");
        if (admin!=null){
            return true;
        }
        response.sendRedirect("/admin/adminLogin");
        return false;
    }
}
