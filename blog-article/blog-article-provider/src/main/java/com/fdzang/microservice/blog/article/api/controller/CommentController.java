package com.fdzang.microservice.blog.article.api.controller;

import com.fdzang.microservice.blog.article.api.service.CommentService;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:39
 */

@RestController
@RequestMapping("/zuul/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/getCommentByArticleId")
    public ApiResult getCommentByArticleId(@RequestParam("id")String id){
        return ok(commentService.getCommentByArticleId(id));
    }

    @GetMapping("/getRecentComments")
    public ApiResult getRecentComments(){
        return ok(commentService.getRecentComments());
    }

    @GetMapping("/getCommentsByUserEmail")
    public ApiResult getCommentsByUserEmail(String userEmail){
        return ok(commentService.getCommentsByUserEmail(userEmail));
    }

    @GetMapping("/getComments")
    public ApiResult getComments(){
        return ok(commentService.getComments());
    }

    @GetMapping("/deleteComment")
    public ApiResult deleteComment(@RequestParam("id")String id){
        return ok(commentService.deleteComment(id));
    }

    @GetMapping("/deleteArticleComments")
    public ApiResult deleteArticleComments(@RequestParam("id")String id){
        return ok(commentService.deleteArticleComments(id));
    }

}
