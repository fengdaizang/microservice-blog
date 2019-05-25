package com.fdzang.microservice.blog.article.feign.client.impl;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
            public ApiResult<List<CommentDTO>> getCommentByArticleId(String id) {
                return null;
            }

            @Override
            public ApiResult<List<CommentDTO>> getRecentComments() {
                return null;
            }

            @Override
            public ApiResult<List<CommentDTO>> getCommentsByUserEmail(String userEmail) {
                return null;
            }

            @Override
            public ApiResult<List<CommentDTO>> getComments() {
                return null;
            }

            @Override
            public ApiResult<Boolean> deleteComment(String id) {
                return null;
            }
        };
    }
}