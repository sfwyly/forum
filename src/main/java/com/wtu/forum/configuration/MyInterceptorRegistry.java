package com.wtu.forum.configuration;

import com.wtu.forum.interceptor.UserAuthorityInterceptor;
import com.wtu.forum.service.common.UserRequestURIAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyInterceptorRegistry
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/7 11:40
 * @Version 1.0
 **/
@Configuration

public class MyInterceptorRegistry implements  WebMvcConfigurer{

    @Autowired
    private UserRequestURIAuthority userRequestURIAuthority;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthorityInterceptor(userRequestURIAuthority)).addPathPatterns("/**");//配置
    }
}
