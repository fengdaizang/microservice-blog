package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.web.feign.console.ConsoleUserClient;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:16
 */
@Controller
@RequestMapping("/admin")
@Validated
public class UserController extends BaseController {

    @Autowired
    private ConsoleUserClient consoleUserClient;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public String userLogin(@RequestParam("username")String username,
            @RequestParam("password")String password){

        ApiResult<UserDTO> result=consoleUserClient.userLogin(username, password);
        request.getSession().setAttribute("UserSession",result.getData());

        return "index";
    }
}
