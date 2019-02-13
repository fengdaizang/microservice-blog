package com.fdzang.microservice.blog.web.feign.article.impl;

import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.web.feign.article.TagClient;
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
public class TagHystrix implements FallbackFactory<TagClient> {

    @Override
    public TagClient create(Throwable throwable) {
        return new TagClient() {
            @Override
            public ApiResult<List<TagDTO>> getMostUsedTags() {
                return null;
            }

            @Override
            public ApiResult<List<TagDTO>> getTags() {
                return null;
            }

            @Override
            public ApiResult<TagDTO> getTagByTitle(String title) {
                return null;
            }
        };
    }

}
