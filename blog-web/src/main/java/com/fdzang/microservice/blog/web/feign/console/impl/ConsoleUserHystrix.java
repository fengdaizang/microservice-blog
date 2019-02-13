package com.fdzang.microservice.blog.web.feign.console.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.web.feign.console.ConsoleUserClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:18
 */
@Component
@Slf4j
public class ConsoleUserHystrix implements FallbackFactory<ConsoleUserClient> {

    @Override
    public ConsoleUserClient create(Throwable throwable) {
        return new ConsoleUserClient() {
            @Override
            public ApiResult<UserDTO> userLogin(String username, String password) {
                return null;
            }
        };
    }

}
