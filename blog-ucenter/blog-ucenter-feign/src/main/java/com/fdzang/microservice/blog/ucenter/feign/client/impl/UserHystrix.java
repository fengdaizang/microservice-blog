package com.fdzang.microservice.blog.ucenter.feign.client.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
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
            public ApiResult<UserDTO> getUserByEmail(String email) {
                return null;
            }

            @Override
            public ApiResult<UserDTO> userLogin(String username, String password) {
                return null;
            }

            @Override
            public ApiResult<Boolean> addUser(UserDTO userDTO) {
                return null;
            }
        };
    }
}