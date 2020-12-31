package com.example.SpringMySQL.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestInterceptorAppConfig implements WebMvcConfigurer {
    @Autowired
    RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/demo/login");
        pathPatterns.add("/docs/**");
        pathPatterns.add("/swagger/**");
        registry.addInterceptor(requestInterceptor).excludePathPatterns(pathPatterns); //register interceptor to InterceptorRegistry, without this, Interceptor will not work
    }
}
