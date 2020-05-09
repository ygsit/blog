package com.blog.config.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //已经登录了就不拦截
        if(request.getSession().getAttribute("user") != null){
            return true;
        }
        //跳转到登录页面
        request.setAttribute("permission_err", "暂无权限，请先登录！");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }

}
