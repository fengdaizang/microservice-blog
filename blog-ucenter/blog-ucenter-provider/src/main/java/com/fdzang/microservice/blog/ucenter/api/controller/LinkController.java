package com.fdzang.microservice.blog.ucenter.api.controller;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.ucenter.api.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
