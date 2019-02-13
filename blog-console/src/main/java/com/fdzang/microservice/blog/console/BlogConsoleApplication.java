package com.fdzang.microservice.blog.console;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = { "com.fdzang.microservice.blog" })
public class BlogConsoleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlogConsoleApplication.class)
                .web(true)
                .run(args);
    }
}
