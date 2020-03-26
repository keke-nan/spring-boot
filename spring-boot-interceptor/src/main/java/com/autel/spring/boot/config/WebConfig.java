package com.autel.spring.boot.config;

import com.autel.spring.boot.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 定义拦截器
 * @author A20019
 * @date 2020/3/26
 */

@Configuration
public class WebConfig  extends WebMvcConfigurationSupport {

    /**
     * 可定义多个拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //定义过滤拦截器的url，拦截所有请求
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
