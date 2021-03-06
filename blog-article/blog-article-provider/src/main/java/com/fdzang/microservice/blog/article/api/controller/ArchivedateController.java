package com.fdzang.microservice.blog.article.api.controller;

import com.fdzang.microservice.blog.article.api.service.ArchivedateService;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:42
 */
@RestController
@RequestMapping("/zuul/archivedate")
public class ArchivedateController extends BaseController {

    @Autowired
    private ArchivedateService archivedateService;

    @GetMapping("/getArchives")
    public ApiResult getArchives(){
        return ok(archivedateService.getArchives());
    }

    @GetMapping("/getArchiveByTime")
    public ApiResult getArchiveByTime(@RequestParam("year")Integer year,
                                      @RequestParam("month")Integer month){

        return ok(archivedateService.getArchiveByTime(year, month));
    }

    @GetMapping("/addArticleAndArchive")
    public ApiResult addArticleAndArchive(@RequestParam("id")String id, @RequestParam("isPush") Boolean isPush){
        return ok(archivedateService.addArticleAndArchive(id, isPush));
    }

    @GetMapping("/updateArticleAndArchive")
    public ApiResult updateArticleAndArchive(@RequestParam("id")String id,
                                             @RequestParam("oldPush") Boolean oldPush,
                                             @RequestParam("newPush") Boolean newPush){
        return ok(archivedateService.updateArticleAndArchive(id, oldPush, newPush));
    }

    @GetMapping("/deleteArticleAndArchive")
    public ApiResult deleteArticleAndArchive(@RequestParam("id")String id,
                                             @RequestParam("isPush") Boolean isPush){
        return ok(archivedateService.deleteArticleAndArchive(id, isPush));
    }


}
