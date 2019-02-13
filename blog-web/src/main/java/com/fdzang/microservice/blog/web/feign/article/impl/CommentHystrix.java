package com.fdzang.microservice.blog.web.feign.article.impl;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.web.feign.article.CommentClient;
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
public class CommentHystrix implements FallbackFactory<CommentClient> {

    @Override
    public CommentClient create(Throwable throwable) {
        return new CommentClient() {
            @Override
            public ApiResult<List<CommentDTO>> getCommentByArticleId(String id) {
                return null;
            }
        };
    }

}
