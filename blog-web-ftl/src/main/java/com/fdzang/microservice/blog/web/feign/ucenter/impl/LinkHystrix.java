package com.fdzang.microservice.blog.web.feign.ucenter.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.web.feign.ucenter.LinkClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:18
 */
@Component
@Slf4j
public class LinkHystrix implements FallbackFactory<LinkClient> {

    @Override
    public LinkClient create(Throwable throwable) {
        return new LinkClient() {
            @Override
            public ApiResult<List<LinkDTO>> getLinks() {
                return null;
            }
        };
    }

}
