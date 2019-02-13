package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.feign.client.impl.CommentHystrix;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:45
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = CommentHystrix.class)
public interface CommentClient {

    @GetMapping("/zuul/comment/getCommentByArticleId")
    ApiResult getCommentByArticleId(@RequestParam("id")String id);
}
