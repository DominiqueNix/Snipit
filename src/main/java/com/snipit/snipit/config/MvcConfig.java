package com.snipit.snipit.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.snipit.snipit.interceptor.AuthInterceptor;

//adds interceptor 
public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/users");
    }
    
}
