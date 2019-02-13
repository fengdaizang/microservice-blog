package com.fdzang.microservice.blog.ucenter.feign.client.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.OptionsClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:12
 */
@Component
@Slf4j
public class OptionsHystrix implements FallbackFactory<OptionsClient> {
    @Override
    public OptionsClient create(Throwable throwable) {
        return new OptionsClient() {
            @Override
            public ApiResult getAllOptions() {
                return null;
            }

            @Override
            public ApiResult getOptionById(String id) {
                return null;
            }

            @Override
            public ApiResult updateOption(OptionsDTO option) {
                return null;
            }
        };
    }
}