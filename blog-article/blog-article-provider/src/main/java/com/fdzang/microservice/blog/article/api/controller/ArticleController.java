package com.fdzang.microservice.blog.article.api.controller;

import com.fdzang.microservice.blog.article.api.service.ArticleService;
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

    @GetMapping("/getArticleByPermalink")
    public ApiResult getArticleByPermalink(@RequestParam("permalink")String permalink){
        return ok(articleService.getArticleByPermalink(permalink));
    }

    @RequestMapping(value = "/addArticleViewCount",method = RequestMethod.POST)
    public ApiResult addArticleViewCount(@RequestParam("id") String id){
        return ok(articleService.addArticleViewCount(id));
    }

    @RequestMapping(value = "/getMostCommentArticles",method = RequestMethod.GET)
    public ApiResult getMostCommentArticles(){
        return ok(articleService.getMostCommentArticles());
    }

    @RequestMapping(value = "/getMostViewCountArticles",method = RequestMethod.GET)
    public ApiResult getMostViewCountArticles(){
        return ok(articleService.getMostViewCountArticles());
    }

    @RequestMapping(value = "/getArticlesByTagId",method = RequestMethod.GET)
    public ApiResult getArticlesByTagId(@RequestParam("tagId") String tagId,
                                        @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getArticlesByTagId(tagId,pageNo,pageSize));
    }

    @RequestMapping(value = "/getArticlesByArchiveId",method = RequestMethod.GET)
    public ApiResult getArticlesByArchiveId(@RequestParam("archiveId") String archiveId,
                                            @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return ok(articleService.getArticlesByArchiveId(archiveId,pageNo,pageSize));
    }
}
