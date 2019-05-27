package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private HttpSession session;

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
        List<ArticleDTO> articles=data.getResult();

        session.setAttribute(Constant.Session.ARTICLES,articles);
        session.setAttribute(Constant.Session.PAGINATIONPAGECOUNT,data.getTotalPage());
        session.setAttribute(Constant.Session.PAGINATIONPAGENUMS,data.getPages());
        session.setAttribute(Constant.Session.PAGINATIONPREVIOUSPAGENUM,data.getPrevious());
        session.setAttribute(Constant.Session.PAGINATIONCURRENTPAGENUM,data.getPageNo());
        session.setAttribute(Constant.Session.PAGINATIONNEXTPAGENUM,data.getNext());

        return Constant.IndexHtml.INDEX;
    }

    @RequestMapping("/admin/index")
    public String adminIndex(){
        UserDTO user = (UserDTO)session.getAttribute(Constant.Session.USER);
        if(user==null){
            return Constant.AdminHtml.LOGIN;
        }else{
            return Constant.AdminHtml.INDEX;
        }
    }

    @RequestMapping("/search")
    public String search(String keyword,Integer pageNO,Integer pageSize) throws Exception{

        ApiResult<PageDTO<ArticleDTO>> result=articleClient.getArticles(keyword, pageNO, pageSize);
        PageDTO<ArticleDTO> data=result.getData();
        int paginationPageCount=data.getTotalPage();
        List<ArticleDTO> articles=data.getResult();

        session.setAttribute(Constant.Session.ARTICLES,articles);
        session.setAttribute(Constant.Session.PAGINATIONPAGECOUNT,paginationPageCount);

        return Constant.IndexHtml.SEARCH;
    }

    @RequestMapping("/register")
    public String showRegister(){
        return Constant.AdminHtml.REGISTER;
    }

    @RequestMapping("/login")
    public String login(){
        return Constant.AdminHtml.LOGIN;
    }
}
