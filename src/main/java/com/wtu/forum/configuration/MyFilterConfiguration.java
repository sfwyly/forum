package com.wtu.forum.configuration;

import com.wtu.forum.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyFilterConfiguration
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/8 21:08
 * @Version 1.0
 **/
@Configuration
public class MyFilterConfiguration {

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegister(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new RequestFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
