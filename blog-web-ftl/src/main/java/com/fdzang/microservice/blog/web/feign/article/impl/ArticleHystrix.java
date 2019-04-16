package com.fdzang.microservice.blog.web.feign.article.impl;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.web.feign.article.ArticleClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/10 15:39
 */
@Component
@Slf4j
public class ArticleHystrix implements FallbackFactory<ArticleClient> {

    @Override
    public ArticleClient create(Throwable throwable) {
        return new ArticleClient() {
            @Override
            public ApiResult<PageDTO<ArticleDTO>> getArticles(String keyword, Integer pageNo, Integer pageSize) {
                return null;
            }

            @Override
            public ApiResult<ArticleDTO> getArticleByPermalink(String permalink) {
                return null;
            }

            @Override
            public ApiResult addArticleViewCount(String id) {
                return null;
            }

            @Override
            public ApiResult<List<ArticleDTO>> getMostCommentArticles() {
                return null;
            }

            @Override
            public ApiResult<List<ArticleDTO>> getMostViewCountArticles() {
                return null;
            }

            @Override
            public ApiResult<PageDTO<ArticleDTO>> getArticlesByTagId(String tagId, Integer pageNo, Integer pageSize) {
                return null;
            }

            @Override
            public ApiResult<PageDTO<ArticleDTO>> getArticlesByArchiveId(String archiveId, Integer pageNo, Integer pageSize) {
                return null;
            }
        };
    }

}