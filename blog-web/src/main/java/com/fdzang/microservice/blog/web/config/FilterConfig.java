package com.fdzang.microservice.blog.web.config;

import com.fdzang.microservice.blog.web.filter.InitFilter;
import com.fdzang.microservice.blog.web.filter.PermalinkFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class FilterConfig {

    @Bean
    public FilterRegistrationBean getInitFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new InitFilter());
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/**");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        registrationBean.setEnabled(true);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean getPermalinkFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new PermalinkFilter());
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/**");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(2);
        registrationBean.setEnabled(true);

        return registrationBean;
    }
}
