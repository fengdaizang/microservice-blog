package com.fdzang.microservice.blog.article.feign.client.impl;

import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:44
 */
@Component
@Slf4j
public class TagHystrix implements FallbackFactory<TagClient> {
    @Override
    public TagClient create(Throwable throwable) {
        return new TagClient() {
            @Override
            public ApiResult getMostUsedTags() {
                return null;
            }

            @Override
            public ApiResult getTags() {
                return null;
            }

            @Override
            public ApiResult getTagByTitle(String title) {
                return null;
            }

            @Override
            public ApiResult addArticleAndTag(String tags, String id, Boolean isPush) {
                return null;
            }

            @Override
            public ApiResult<Boolean> deleteArticleAndTag(String id, Boolean isPush) {
                return null;
            }

            @Override
            public ApiResult<Integer> deleteNoUseTag() {
                return null;
            }
        };
    }
}