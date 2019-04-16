package com.fdzang.microservice.blog.web.config;

import com.fdzang.microservice.blog.web.listener.OnlineUserListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

public class WebConfig {

    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new OnlineUserListener());
        srb.setOrder(6);
        System.out.println("listener");
        return srb;
    }


}
