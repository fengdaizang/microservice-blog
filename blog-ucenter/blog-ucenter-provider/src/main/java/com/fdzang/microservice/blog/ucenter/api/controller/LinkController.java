package com.fdzang.microservice.blog.ucenter.api.controller;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.ucenter.api.service.LinkService;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:21
 */
@RestController
@RequestMapping("/zuul/link")
public class LinkController extends BaseController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getLinks")
    public ApiResult getLinks(){
        return ok(linkService.getLinks());
    }

    @GetMapping("/getLinksByKeyword")
    public ApiResult getLinksByKeyword(@RequestParam("keyword") String keyword){
        return ok(linkService.getLinksByKeyWord(keyword));
    }

    @PostMapping("/addLink")
    public ApiResult addLink(@RequestBody LinkDTO link){
        return ok(linkService.addLink(link));
    }

    @GetMapping("/getLinksById")
    public ApiResult getLinksById(@RequestParam("id")String id){
        return ok(linkService.getLinkById(id));
    }

    @PostMapping("/updateLink")
    public ApiResult updateLink(@RequestBody LinkDTO link){
        return ok(linkService.updateLink(link));
    }

    @GetMapping("/deleteLink")
    public ApiResult deleteLink(@RequestParam("id")String id){
        return ok(linkService.deleteLink(id));
    }
}
