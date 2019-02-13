package com.fdzang.microservice.blog.ucenter.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan(value = "com.fdzang.microservice.blog.ucenter.dao.mapper")
@EnableFeignClients(basePackages = { "com.fdzang.microservice.blog" })
public class BlogUcenterProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlogUcenterProviderApplication.class)
                .web(true)
                .run(args);
    }
}
