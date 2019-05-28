package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.ArchivedateClient;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.OptionsClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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
    private OptionsClient optionsClient;

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

        optionsClient.incrementById(Constant.Static.BLOG_ARTICLE_COUNT,1);

        Boolean isPush=false;
        if(Constant.Article.YES.equals(article.getArticleIsPublished())){
            isPush=true;
            optionsClient.incrementById(Constant.Static.PUBLISHED_BLOG_ARTICLE_COUNT,1);
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
        if(Constant.Article.YES.equals(articleDTO.getArticleIsPublished())){
            oldPush=true;
            optionsClient.incrementById(Constant.Static.PUBLISHED_BLOG_ARTICLE_COUNT,-1);
        }
        if(Constant.Article.YES.equals(article.getArticleIsPublished())){
            newPush=true;
            optionsClient.incrementById(Constant.Static.PUBLISHED_BLOG_ARTICLE_COUNT,1);
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
        List<CommentDTO> articleComments=(List<CommentDTO> )
                CoventUtils.getApiResultData(commentClient.getCommentByArticleId(id));

        optionsClient.incrementById(Constant.Static.BLOG_ARTICLE_COUNT,-1);
        optionsClient.incrementById(Constant.Static.BLOG_COMMENT_COUNT,articleComments.size());
        Boolean isPush=false;
        if(Constant.Article.YES.equals(articleDTO.getArticleIsPublished())){
            isPush=true;
            optionsClient.incrementById(Constant.Static.PUBLISHED_BLOG_ARTICLE_COUNT,-1);
        }

        Boolean articleBool=(Boolean) CoventUtils.getApiResultData(articleClient.deleteArticle(id));
        Boolean commentBool=(Boolean) CoventUtils.getApiResultData(commentClient.deleteArticleComments(id));
        Boolean tagBool=(Boolean) CoventUtils.getApiResultData(tagClient.deleteArticleAndTag(id,isPush));
        Boolean archivedateBool=(Boolean) CoventUtils.getApiResultData(
                archivedateClient.deleteArticleAndArchive(id,isPush));

        return articleBool&&commentBool&&tagBool&&archivedateBool;
    }

    @GetMapping("/article/mgr")
    public String articleMgr(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,
                             @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             HashMap<String,Object> map){
        UserDTO userDTO=getCurrentUser();
        PageDTO<ArticleDTO> articles=null;

        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isEmpty(keyword)){
            keyword="";
        }
        if(Constant.UserRole.DEFAULT.equals(userDTO.getUserRole())){
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getArticlesByUserEmail(
                            userDTO.getUserEmail(),keyword,pageNo,pageSize));
        }else{
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getArticles(keyword,pageNo,pageSize));
        }

        map.put(Constant.Page.PAGE,articles);
        map.put(Constant.Session.PATH,"/article/mgr?pageNo=");

        return Constant.AdminHtml.ARTICLE;
    }

    @ResponseBody
    @GetMapping("/article/search")
    public String userSearch(@RequestParam("keyword")String keyword){
        session.setAttribute(Constant.Session.KEYWORD,keyword);

        return keyword;
    }

    @GetMapping("/article/draft/mgr")
    public String draftMgr(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,
                           @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                           HashMap<String,Object> map){
        UserDTO userDTO=getCurrentUser();
        PageDTO<ArticleDTO>  articles=null;

        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isEmpty(keyword)){
            keyword="";
        }else{
            session.removeAttribute(Constant.Session.KEYWORD);
        }
        if(Constant.UserRole.DEFAULT.equals(userDTO.getUserRole())){
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getDraftsByUserEmail(
                            userDTO.getUserEmail(),keyword,pageNo,pageSize));
        }else{
            articles=(PageDTO<ArticleDTO>)CoventUtils.getApiResultData(
                    articleClient.getDrafts(keyword,pageNo,pageSize));
        }

        map.put(Constant.Page.PAGE,articles);
        map.put(Constant.Session.PATH,"/article/draft/mgr?pageNo=");

        return Constant.AdminHtml.DRAFT;
    }
}
