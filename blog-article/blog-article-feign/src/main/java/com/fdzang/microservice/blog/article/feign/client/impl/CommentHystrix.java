package com.fdzang.microservice.blog.article.feign.client.impl;

import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:45
 */
@Component
@Slf4j
public class CommentHystrix implements FallbackFactory<CommentClient> {
    @Override
    public CommentClient create(Throwable throwable) {
        return new CommentClient() {
            @Override
            public ApiResult getCommentByArticleId(String id) {
                return null;
            }
        };
    }
}