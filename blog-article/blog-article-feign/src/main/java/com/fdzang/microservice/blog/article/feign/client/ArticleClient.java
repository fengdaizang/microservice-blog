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

    @GetMapping("/zuul/article/getArticlesByUserEmail")
    ApiResult<PageDTO<ArticleDTO>> getArticlesByUserEmail(@RequestParam("userEmail")String userEmail,
                                                          @RequestParam("keyword")String keyword,
                                                          @RequestParam("pageNo")Integer pageNo,
                                                          @RequestParam("pageSize")Integer pageSize);

    @GetMapping("/zuul/article/getArticleByPermalink")
    ApiResult<ArticleDTO> getArticleByPermalink(@RequestParam("permalink")String permalink);

    @GetMapping("/zuul/article/addArticleViewCount")
    ApiResult<Boolean> addArticleViewCount(@RequestParam("id")String id);

    @GetMapping("/zuul/article/getMostCommentArticles")
    ApiResult<List<ArticleDTO>> getMostCommentArticles();

    @GetMapping("/zuul/article/getMostViewCountArticles")
    ApiResult<List<ArticleDTO>> getMostViewCountArticles();

    @GetMapping("/zuul/article/getArticlesByTagId")
    ApiResult<PageDTO<ArticleDTO>> getArticlesByTagId(String tagId);

    @GetMapping("/zuul/article/getArticlesByArchiveId")
    ApiResult<PageDTO<ArticleDTO>> getArticlesByArchiveId(@RequestParam("archiveId") String archiveId,
                                                          @RequestParam("pageNo")Integer pageNo,
                                                          @RequestParam("pageSize")Integer pageSize);

    @PostMapping("/zuul/article/addArticle")
    ApiResult<Boolean> addArticle(@RequestBody ArticleDTO article);

    @GetMapping("/zuul/article/getArticleById")
    ApiResult<ArticleDTO> getArticleById(@RequestParam("id")String id);

    @PostMapping("/zuul/article/updateArticle")
    ApiResult<Boolean> updateArticle(@RequestBody ArticleDTO article);

    @GetMapping("/zuul/article/pushTop")
    ApiResult<Boolean> pushTop(@RequestParam("id") String id,
            @RequestParam("isTop") String isTop);

    @GetMapping("/zuul/article/deleteArticle")
    ApiResult<Boolean> deleteArticle(@RequestParam("id") String id);

    @GetMapping("/zuul/article/getDrafts")
    ApiResult<PageDTO<ArticleDTO>> getDrafts(@RequestParam("keyword")String keyword,
                                             @RequestParam("pageNo")Integer pageNo,
                                             @RequestParam("pageSize")Integer pageSize);

    @GetMapping("/zuul/article/getDraftsByUserEmail")
    ApiResult<PageDTO<ArticleDTO>> getDraftsByUserEmail(@RequestParam("userEmail")String userEmail,
                                                        @RequestParam("keyword")String keyword,
                                                        @RequestParam("pageNo")Integer pageNo,
                                                        @RequestParam("pageSize")Integer pageSize);

}
