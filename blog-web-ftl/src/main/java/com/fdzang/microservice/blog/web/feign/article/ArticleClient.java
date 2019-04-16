package com.fdzang.microservice.blog.web.feign.article;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.impl.ArticleHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/10 15:38
 */
@FeignClient(value = Constant.ServiceName.BLOG_ARTICLE,fallbackFactory = ArticleHystrix.class)
public interface ArticleClient {
    @RequestMapping(value = "/zuul/article/getArticles",method = RequestMethod.GET)
    ApiResult<PageDTO<ArticleDTO>> getArticles(@RequestParam("keyword") String keyword,
                                               @RequestParam("pageNo") Integer pageNo,
                                               @RequestParam("pageSize") Integer pageSize);

    @RequestMapping(value = "/zuul/article/getArticleByPermalink",method = RequestMethod.GET)
    ApiResult<ArticleDTO> getArticleByPermalink(@RequestParam("permalink") String permalink);

    @RequestMapping(value = "/zuul/article/addArticleViewCount",method = RequestMethod.POST)
    ApiResult addArticleViewCount(@RequestParam("id") String id);

    @RequestMapping(value = "/zuul/article/getMostCommentArticles",method = RequestMethod.GET)
    ApiResult<List<ArticleDTO>> getMostCommentArticles();

    @RequestMapping(value = "/zuul/article/getMostViewCountArticles",method = RequestMethod.GET)
    ApiResult<List<ArticleDTO>> getMostViewCountArticles();

    @RequestMapping(value = "/zuul/article/getArticlesByTagId",method = RequestMethod.GET)
    ApiResult<PageDTO<ArticleDTO>> getArticlesByTagId(@RequestParam("tagId") String tagId,
                                                      @RequestParam("pageNo") Integer pageNo,
                                                      @RequestParam("pageSize") Integer pageSize);

    @RequestMapping(value = "/zuul/article/getArticlesByArchiveId",method = RequestMethod.GET)
    ApiResult<PageDTO<ArticleDTO>> getArticlesByArchiveId(@RequestParam("archiveId") String archiveId,
                                                          @RequestParam("pageNo") Integer pageNo,
                                                          @RequestParam("pageSize") Integer pageSize);
}
