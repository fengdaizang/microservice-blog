package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author tanghu
 * @Date: 2019/4/16 21:23
 */
@Controller
public class ArticleController extends BaseController {

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/article")
    public String article(){

        return Constant.IndexHtml.ARTICLE;
    }

}
