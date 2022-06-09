package com.wtu.forum.configuration;

import com.wtu.forum.listener.MySessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyListenerConfiguration
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/8 21:14
 * @Version 1.0
 **/
@Configuration
public class MyListenerConfiguration {

    /**
     * 注册session监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean listenerRegistrationBean(){

        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(new MySessionListener());

        return listenerRegistrationBean;
    }

}
