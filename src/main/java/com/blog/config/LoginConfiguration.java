package com.blog.config;

import com.blog.config.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        //首页不拦截
                        "/", "/index", "/index.html",
                        //登录注册不拦截
                        "/login", "/getVerifyCode", "/user/**",
                        //静态资源不拦截
                        "/css/**", "/images/**", "/js/**", "/layui/**", "/lib/**");
    }

}

