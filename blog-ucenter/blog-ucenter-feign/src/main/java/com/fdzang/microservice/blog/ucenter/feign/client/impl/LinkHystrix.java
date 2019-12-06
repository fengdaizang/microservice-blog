package com.fdzang.microservice.blog.ucenter.feign.client.impl;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.LinkClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 10:13
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

            @Override
            public ApiResult<List<LinkDTO>> getLinksByKeyword(String keyword) {
                return null;
            }

            @Override
            public ApiResult<Boolean> addLink(LinkDTO link) {
                return null;
            }

            @Override
            public ApiResult<LinkDTO> getLinksById(String id) {
                return null;
            }

            @Override
            public ApiResult<Boolean> updateLink(LinkDTO link) {
                return null;
            }

            @Override
            public ApiResult<Boolean> deleteLink(String id) {
                return null;
            }
        };
    }
}