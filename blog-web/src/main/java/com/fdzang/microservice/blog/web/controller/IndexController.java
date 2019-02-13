package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.ArticleClient;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping({"/index","","/"})
    public String index(Integer pageNO,Integer pageSize) throws Exception{
        if(pageNO==null){
            pageNO=0;
        }
        if(pageSize==null){
            pageSize=10;
        }
        ApiResult<PageDTO<ArticleDTO>> result=articleClient.getArticles("", pageNO, pageSize);
        PageDTO<ArticleDTO> data=result.getData();
        int paginationPageCount=data.getTotalPage();
        List<ArticleDTO> articles=data.getResult();

        request.getSession().setAttribute(Constant.Session.ARTICLES,articles);
        request.getSession().setAttribute(Constant.Session.PAGINATIONPAGECOUNT,paginationPageCount);

        return Constant.Html.INDEX;
    }

    @RequestMapping("/search")
    public String search(String keyword,Integer pageNO,Integer pageSize) throws Exception{

        ApiResult<PageDTO<ArticleDTO>> result=articleClient.getArticles(keyword, pageNO, pageSize);
        PageDTO<ArticleDTO> data=result.getData();
        int paginationPageCount=data.getTotalPage();
        List<ArticleDTO> articles=data.getResult();

        request.getSession().setAttribute(Constant.Session.ARTICLES,articles);
        request.getSession().setAttribute(Constant.Session.PAGINATIONPAGECOUNT,paginationPageCount);

        return Constant.Html.SEARCH;
    }

    @RequestMapping("register")
    public String showRegister(){

        return Constant.Html.REGISTER;
    }

    @RequestMapping("login")
    public String login(){

        return Constant.Html.LOGIN;
    }
}
