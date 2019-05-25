package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.impl.ArticleHystrix;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 11:13
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = ArticleHystrix.class)
public interface ArticleClient {

    @GetMapping("/zuul/article/getArticles")
    ApiResult<PageDTO<ArticleDTO>> getArticles(@RequestParam("keyword")String keyword,
                                               @RequestParam("pageNo")Integer pageNo,
                                               @RequestParam("pageSize")Integer pageSize);

    @GetMapping("/zuul/article/getArticleByPermalink")
    ApiResult<ArticleDTO> getArticleByPermalink(@RequestParam("permalink")String permalink);

    @GetMapping(value = "/zuul/article/addArticleViewCount")
    ApiResult<Boolean> addArticleViewCount(@RequestParam("id")String id);

    @GetMapping(value = "/zuul/article/getMostCommentArticles")
    ApiResult<List<ArticleDTO>> getMostCommentArticles();

    @GetMapping(value = "/zuul/article/getMostViewCountArticles")
    ApiResult<List<ArticleDTO>> getMostViewCountArticles();

    @GetMapping(value = "/zuul/article/getArticlesByTagId")
    ApiResult<PageDTO<ArticleDTO>> getArticlesByTagId(String tagId);

    @GetMapping(value = "/zuul/article/getArticlesByArchiveId")
    ApiResult<PageDTO<ArticleDTO>> getArticlesByArchiveId(@RequestParam("archiveId") String archiveId,
                                     @RequestParam("pageNo")Integer pageNo,
                                     @RequestParam("pageSize")Integer pageSize);

    @PostMapping(value = "/zuul/article/addArticle")
    ApiResult<Boolean> addArticle(@RequestBody ArticleDTO article);

}
