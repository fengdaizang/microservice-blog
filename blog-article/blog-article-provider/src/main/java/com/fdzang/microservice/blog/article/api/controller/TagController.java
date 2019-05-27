package com.fdzang.microservice.blog.article.api.controller;

import com.fdzang.microservice.blog.article.api.service.TagService;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/8 11:11
 */

@RestController
@RequestMapping("/zuul/tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/getMostUsedTags")
    public ApiResult getMostUsedTags(){
        return ok(tagService.getMostUsedTags());
    }

    @GetMapping(value = "/getTags")
    public ApiResult getTags(){
        return ok(tagService.getTags());
    }

    @GetMapping(value = "/getTagByTitle")
    public ApiResult getTagByTitle(@RequestParam("title") String title){
        return ok(tagService.getTagByTitle(title));
    }

    @GetMapping(value = "/addArticleAndTag")
    public ApiResult addArticleAndTag(@RequestParam("tags") String tags,
                                      @RequestParam("id") String id,
                                      @RequestParam("isPush") Boolean isPush){
        return ok(tagService.addArticleAndTag(tags,id,isPush));
    }

    @GetMapping(value = "/updateArticleAndTag")
    public ApiResult updateArticleAndTag(@RequestParam("tags") String tags,
                                         @RequestParam("id") String id,
                                         @RequestParam("oldPush") Boolean oldPush,
                                         @RequestParam("newPush") Boolean newPush){
        return ok(tagService.updateArticleAndTag(tags,id,oldPush,newPush));
    }

    @GetMapping(value = "/deleteArticleAndTag")
    public ApiResult deleteArticleAndTag(@RequestParam("id") String id,
                                         @RequestParam("isPush") Boolean isPush){
        return ok(tagService.deleteArticleAndTag(id,isPush));
    }

    @GetMapping(value = "/deleteNoUseTag")
    public ApiResult deleteNoUseTag(){
        return ok(tagService.deleteTagNoUse());
    }


}
