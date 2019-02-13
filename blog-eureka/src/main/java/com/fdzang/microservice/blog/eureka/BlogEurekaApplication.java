package com.fdzang.microservice.blog.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BlogEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogEurekaApplication.class, args);
    }
}
