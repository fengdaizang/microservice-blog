package com.fdzang.microservice.blog.web.feign.ucenter.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.web.feign.ucenter.UserClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:18
 */
@Component
@Slf4j
public class UserHystrix implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public ApiResult<UserDTO> getUserByEmail(String email) {
                return null;
            }
        };
    }

}
