package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.ArchivedateClient;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private TagClient tagClient;

    @Autowired
    private ArchivedateClient archivedateClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/article")
    public String article(){

        return Constant.IndexHtml.ARTICLE;
    }

    @GetMapping("/article/add")
    public String addArticle(){

        return Constant.AdminHtml.ADD_ARTICLE;
    }

    @ResponseBody
    @PostMapping("/article/push")
    public Boolean articlePush(@ModelAttribute ArticleDTO article){
        UserDTO user=getCurrentUser();
        String id= TimeUtils.getTimestamp();
        article.setArticleAuthorEmail(user.getUserEmail());
        article.setArticleIsPublished(Constant.Article.PUSH);
        article.setId(id);

        Boolean articleBool=(Boolean) CoventUtils.getApiResultData(articleClient.addArticle(article));

        Boolean tagBool=(Boolean) CoventUtils.getApiResultData(
                tagClient.addArticleAndTag(article.getArticleTags(),id,true));

        Boolean archivedateBool=(Boolean) CoventUtils.getApiResultData(
                archivedateClient.addArticleAndArchive(id,true));

        return articleBool&&tagBool&&archivedateBool;
    }

    @ResponseBody
    @PostMapping("/article/draft")
    public Boolean articleDraft(@ModelAttribute ArticleDTO article){
        UserDTO user=getCurrentUser();
        String id= TimeUtils.getTimestamp();
        article.setArticleAuthorEmail(user.getUserEmail());
        article.setArticleIsPublished(Constant.Article.DRAFT);
        article.setId(id);

        Boolean articleBool=(Boolean) CoventUtils.getApiResultData(articleClient.addArticle(article));

        Boolean tagBool=(Boolean) CoventUtils.getApiResultData(
                tagClient.addArticleAndTag(article.getArticleTags(),id,false));

        Boolean archivedateBool=(Boolean) CoventUtils.getApiResultData(
                archivedateClient.addArticleAndArchive(id,false));

        return articleBool&&tagBool&&archivedateBool;
    }

}
