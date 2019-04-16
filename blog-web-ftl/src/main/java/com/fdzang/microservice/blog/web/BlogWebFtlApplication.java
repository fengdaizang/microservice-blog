package com.fdzang.microservice.blog.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(scanBasePackages = {"com.fdzang.microservice.blog"})
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = { "com.fdzang.microservice.blog" })
public class BlogWebFtlApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlogWebFtlApplication.class)
                .web(true)
                .run(args);
    }
}
