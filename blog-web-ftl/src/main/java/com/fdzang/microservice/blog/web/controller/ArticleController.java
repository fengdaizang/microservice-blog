package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.ArchivedateClient;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CommentClient commentClient;

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

    @GetMapping("/article/edit")
    public String editArticle(@RequestParam(value = "id",defaultValue = "")String id,
                             HashMap<String,Object> map){
        if(StringUtils.isNotEmpty(id)){
            ArticleDTO articleDTO=(ArticleDTO)CoventUtils.getApiResultData(
                    articleClient.getArticleById(id));

            map.put(Constant.Article.ARTICLE,articleDTO);
        }
        return Constant.AdminHtml.EDIT_ARTICLE;
    }

    @ResponseBody
    @PostMapping("/article/push")
    public Boolean articlePush(@ModelAttribute ArticleDTO article){
        UserDTO user=getCurrentUser();
        String id= TimeUtils.getTimestamp();

        Boolean isPush=false;
        if(Constant.Article.PUSH.equals(article.getArticleIsPublished())){
            isPush=true;
        }

        article.setArticleAuthorEmail(user.getUserEmail());
        article.setId(id);

        Boolean articleBool=(Boolean) CoventUtils.getApiResultData(articleClient.addArticle(article));

        Boolean tagBool=(Boolean) CoventUtils.getApiResultData(
                tagClient.addArticleAndTag(article.getArticleTags(),id,isPush));

        Boolean archivedateBool=(Boolean) CoventUtils.getApiResultData(
                archivedateClient.addArticleAndArchive(id,isPush));

        return articleBool&&tagBool&&archivedateBool;
    }

    @ResponseBody
    @PostMapping("/article/update")
    public Boolean articleUpdate(@ModelAttribute ArticleDTO article){
        getCurrentUser();
        ArticleDTO articleDTO=(ArticleDTO)CoventUtils.getApiResultData(
                articleClient.getArticleById(article.getId()));

        Boolean oldPush=false;
        Boolean newPush=false;
        if(Constant.Article.PUSH.equals(articleDTO.getArticleIsPublished())){
            oldPush=true;
        }
        if(Constant.Article.PUSH.equals(article.getArticleIsPublished())){
            newPush=true;
        }

        Boolean tagBool=true;
        if(!articleDTO.getArticleTags().equals(article.getArticleTags())){
            tagBool=(Boolean) CoventUtils.getApiResultData(
                    tagClient.updateArticleAndTag(article.getArticleTags(),article.getId(),oldPush,newPush));
        }

        Boolean articleBool=(Boolean) CoventUtils.getApiResultData(articleClient.updateArticle(article));

        Boolean archivedateBool=(Boolean) CoventUtils.getApiResultData(
                archivedateClient.updateArticleAndArchive(article.getId(),oldPush,newPush));

        return articleBool&&tagBool&&archivedateBool;
    }

    @ResponseBody
    @GetMapping("/article/pushTop")
    public Boolean articlePushTop(@RequestParam("id") String id,
                                  @RequestParam("isTop") String isTop){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(articleClient.pushTop(id, isTop));

        return bool;
    }

    @ResponseBody
    @GetMapping("/article/delete")
    public Boolean articleDelete(@RequestParam("id") String id){
        ArticleDTO articleDTO=(ArticleDTO)CoventUtils.getApiResultData(
                articleClient.getArticleById(id));

        Boolean isPush=false;
        if(Constant.Article.PUSH.equals(articleDTO.getArticleIsPublished())){
            isPush=true;
        }

        Boolean articleBool=(Boolean) CoventUtils.getApiResultData(articleClient.deleteArticle(id));
        Boolean commentBool=(Boolean) CoventUtils.getApiResultData(commentClient.deleteArticleComments(id));
        Boolean tagBool=(Boolean) CoventUtils.getApiResultData(tagClient.deleteArticleAndTag(id,isPush));
        Boolean archivedateBool=(Boolean) CoventUtils.getApiResultData(
                archivedateClient.deleteArticleAndArchive(id,isPush));

        return articleBool&&commentBool&&tagBool&&archivedateBool;
    }

    @GetMapping("/article/mgr")
    public String articleMgr(@RequestParam(value = "pageNO",defaultValue = "1")Integer pageNO,
                             @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                             Map<String,Object> map){
        UserDTO userDTO=getCurrentUser();
        PageDTO<ArticleDTO> articles=null;

        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isEmpty(keyword)){
            keyword="";
        }
        if(Constant.UserRole.DEFAULT.equals(userDTO.getUserRole())){
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getArticlesByUserEmail(
                            userDTO.getUserEmail(),keyword,pageNO,pageSize));
        }else{
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getArticles(keyword,pageNO,pageSize));
        }

        map.put(Constant.Session.PAGE,articles);

        return Constant.AdminHtml.ARTICLE;
    }

    @ResponseBody
    @GetMapping("/article/search")
    public String userSearch(@RequestParam("keyword")String keyword){
        session.setAttribute(Constant.Session.KEYWORD,keyword);

        return keyword;
    }

    @GetMapping("/article/draft/mgr")
    public String draftMgr(@RequestParam(value = "pageNO",defaultValue = "1")Integer pageNO,
                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                             Map<String,Object> map){
        UserDTO userDTO=getCurrentUser();
        PageDTO<ArticleDTO>  articles=null;

        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isEmpty(keyword)){
            keyword="";
        }
        if(Constant.UserRole.DEFAULT.equals(userDTO.getUserRole())){
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getDraftsByUserEmail(
                            userDTO.getUserEmail(),keyword,pageNO,pageSize));
        }else{
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getDrafts(keyword,pageNO,pageSize));
        }

        map.put(Constant.Session.PAGE,articles);

        return Constant.AdminHtml.DRAFT;
    }
}
