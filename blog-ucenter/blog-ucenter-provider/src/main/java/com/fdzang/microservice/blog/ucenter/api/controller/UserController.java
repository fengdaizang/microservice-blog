package com.fdzang.microservice.blog.ucenter.api.controller;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.ucenter.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/8 9:42
 */
@RestController
@RequestMapping("/zuul/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserByEmail")
    public ApiResult getUserByEmail(@RequestParam("email")String email){
        return ok(userService.getUserByEmail(email));
    }

}
