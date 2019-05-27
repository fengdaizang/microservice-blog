package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.impl.CommentHystrix;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:45
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = CommentHystrix.class)
public interface CommentClient {

    @GetMapping("/zuul/comment/getCommentByArticleId")
    ApiResult<List<CommentDTO>> getCommentByArticleId(@RequestParam("id")String id);

    @GetMapping("/zuul/comment/getRecentComments")
    ApiResult<List<CommentDTO>> getRecentComments();

    @GetMapping("/zuul/comment/getCommentsByUserEmail")
    ApiResult<List<CommentDTO>> getCommentsByUserEmail(@RequestParam("userEmail")String userEmail);

    @GetMapping("/zuul/comment/getComments")
    ApiResult<List<CommentDTO>> getComments();

    @GetMapping("/zuul/comment/deleteComment")
    ApiResult<Boolean> deleteComment(@RequestParam("id")String id);

    @GetMapping("/zuul/comment/deleteArticleComments")
    ApiResult<Boolean> deleteArticleComments(@RequestParam("id")String id);

    @PostMapping("/zuul/comment/addComment")
    ApiResult<Boolean> addComment(@RequestBody CommentDTO comment);

    @PostMapping("/zuul/comment/replyComment")
    ApiResult<Boolean> replyComment(@RequestBody CommentDTO comment);
}
