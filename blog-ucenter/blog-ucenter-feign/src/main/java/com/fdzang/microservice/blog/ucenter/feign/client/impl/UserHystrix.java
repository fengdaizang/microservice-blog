package com.fdzang.microservice.blog.ucenter.feign.client.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.feign.client.UserClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/8 10:13
 */
@Component
@Slf4j
public class UserHystrix implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public ApiResult getUserByEmail(String email) {
                log.info("call getUserByEmail fail");
                return null;
            }
        };
    }
}