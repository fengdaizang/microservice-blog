package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.impl.ArticleHystrix;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanghu
 * @Date: 2019/1/8 11:13
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = ArticleHystrix.class)
public interface ArticleClient {

    @GetMapping("/zuul/article/getArticles")
    ApiResult getArticles(@RequestParam("keyword")String keyword,
                          @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize);

    @PostMapping("/zuul/article/getArticleByPermalink")
    ApiResult getArticleByPermalink(String permalink);

    @RequestMapping(value = "/zuul/article/addArticleViewCount",method = RequestMethod.POST)
    ApiResult addArticleViewCount(String id);

    @RequestMapping(value = "/zuul/article/getMostCommentArticles",method = RequestMethod.GET)
    ApiResult getMostCommentArticles();

    @RequestMapping(value = "/zuul/article/getMostViewCountArticles",method = RequestMethod.GET)
    ApiResult getMostViewCountArticles();

    @RequestMapping(value = "/zuul/article/getArticlesByTagId",method = RequestMethod.GET)
    ApiResult getArticlesByTagId(String tagId);
}
