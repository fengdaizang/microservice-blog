package com.fdzang.microservice.blog.console.controller;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.console.cache.UserCache;
import com.fdzang.microservice.blog.console.dto.UserWithTokenDTO;
import com.fdzang.microservice.blog.console.service.UserService;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:30
 */
@RestController
@RequestMapping("/zuul/user")
@Validated
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCache userCache;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult login(
            @RequestParam("username") @NotBlank(message = "用户名不能为空") String username,
            @RequestParam("password")  @NotBlank(message = "密码不能为空") String password) {

        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
            throw new BlogException(ErrorCode.TOKEN_EXPIRE, "用户名和密码不能为空");
        }
        log.info("login, username:{}", username);
        UserDTO user = userService.login(username, password);
        String token=userCache.generateToken(user);

        return ok(new UserWithTokenDTO(token,user));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult logout(String token) {
        log.info("logout, token:{}", token);
        userCache.expired(token);
        return ok();
    }
}
