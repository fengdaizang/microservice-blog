package com.fdzang.microservice.blog.article.api.controller;

import com.fdzang.microservice.blog.article.api.service.ArticleService;
import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanghu
 * @Date: 2019/1/8 11:08
 */
@RestController
@RequestMapping("/zuul/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getArticles")
    public ApiResult getArticles(@RequestParam("keyword")String keyword,
            @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getArticles(keyword, pageNo, pageSize));
    }

    @GetMapping("/getArticlesByUserEmail")
    public ApiResult getArticles(@RequestParam("userEmail")String userEmail,@RequestParam("keyword")String keyword,
                                 @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getArticlesByUserEmail(userEmail, keyword, pageNo, pageSize));
    }

    @GetMapping("/getArticleByPermalink")
    public ApiResult getArticleByPermalink(@RequestParam("permalink")String permalink){
        return ok(articleService.getArticleByPermalink(permalink));
    }

    @GetMapping(value = "/addArticleViewCount")
    public ApiResult addArticleViewCount(@RequestParam("id") String id){
        return ok(articleService.addArticleViewCount(id));
    }

    @GetMapping(value = "/getMostCommentArticles")
    public ApiResult getMostCommentArticles(){
        return ok(articleService.getMostCommentArticles());
    }

    @GetMapping(value = "/getMostViewCountArticles")
    public ApiResult getMostViewCountArticles(){
        return ok(articleService.getMostViewCountArticles());
    }

    @GetMapping(value = "/getArticlesByTagId")
    public ApiResult getArticlesByTagId(@RequestParam("tagId") String tagId,
                                        @RequestParam("pageNo")Integer pageNo,
                                        @RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getArticlesByTagId(tagId,pageNo,pageSize));
    }

    @GetMapping(value = "/getArticlesByArchiveId")
    public ApiResult getArticlesByArchiveId(@RequestParam("archiveId") String archiveId,
                                            @RequestParam("pageNo")Integer pageNo,
                                            @RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getArticlesByArchiveId(archiveId,pageNo,pageSize));
    }

    @GetMapping("/getArticleById")
    public ApiResult getArticleById(@RequestParam("id")String id){
        return ok(articleService.getArticleById(id));
    }

    @PostMapping(value = "/addArticle")
    public ApiResult addArticle(@RequestBody ArticleDTO article){
        return ok(articleService.addArticle(article));
    }

    @PostMapping(value = "/updateArticle")
    public ApiResult updateArticle(@RequestBody ArticleDTO article){
        return ok(articleService.updateArticle(article));
    }

    @GetMapping(value = "/pushTop")
    public ApiResult pushTop(@RequestParam("id") String id,
                             @RequestParam("isTop") String isTop){
        return ok(articleService.pushTop(id, isTop));
    }

    @GetMapping(value = "/deleteArticle")
    public ApiResult deleteArticle(@RequestParam("id") String id){
        return ok(articleService.deleteArticle(id));
    }


    @GetMapping("/getDrafts")
    public ApiResult getDrafts(@RequestParam("keyword")String keyword,
                               @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getDrafts(keyword, pageNo, pageSize));
    }

    @GetMapping("/getDraftsByUserEmail")
    public ApiResult getDraftsByUserEmail(@RequestParam("userEmail")String userEmail,@RequestParam("keyword")String keyword,
                                 @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getDraftsByUserEmail(userEmail, keyword, pageNo, pageSize));
    }
}
