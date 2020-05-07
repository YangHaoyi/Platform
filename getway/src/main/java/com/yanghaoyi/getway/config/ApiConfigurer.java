package com.yanghaoyi.getway.config;

import com.yanghaoyi.getway.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
@Configuration
public class ApiConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration = registry.addInterceptor(new AuthorizationInterceptor());     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("api/v1");                    //所有路径都被拦截
        registration.excludePathPatterns("/authcode","/login","/test");       //添加不拦截路径
    }
}
