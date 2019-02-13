package com.fdzang.microservice.blog.article.feign.client.impl;

import com.fdzang.microservice.blog.article.feign.client.ArchivedateClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:46
 */
@Component
@Slf4j
public class ArchivedateHystrix implements FallbackFactory<ArchivedateClient> {
    @Override
    public ArchivedateClient create(Throwable throwable) {
        return new ArchivedateClient() {

        };
    }
}