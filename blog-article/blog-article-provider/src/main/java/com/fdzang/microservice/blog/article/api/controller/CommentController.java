package com.fdzang.microservice.blog.article.api.controller;

import com.fdzang.microservice.blog.article.api.service.CommentService;
import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addComment")
    public ApiResult addComment(@RequestBody CommentDTO comment){
        return ok(commentService.addComment(comment));
    }

    @PostMapping("/replyComment")
    public ApiResult replyComment(@RequestBody CommentDTO comment){
        return ok(commentService.replyComment(comment));
    }

}
