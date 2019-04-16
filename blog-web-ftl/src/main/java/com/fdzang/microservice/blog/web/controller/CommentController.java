package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.CommentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tanghu
 * @Date: 2019/4/16 21:41
 */
public class CommentController extends BaseController {

    @Autowired
    private CommentClient commentClient;

    @GetMapping
    public String dynamic(){

        return Constant.Html.DYNAMIC;
    }
}
