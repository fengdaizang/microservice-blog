package com.fdzang.microservice.blog.ucenter.api.controller;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.ucenter.api.service.UserService;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/userLogin")
    public ApiResult userLogin(
            @RequestParam("username") @NotBlank(message = "用户名不能为空") String username,
            @RequestParam("password")  @NotBlank(message = "密码不能为空") String password) {

        if (StringUtils.isEmpty(username)|| StringUtils.isEmpty(password)) {
            throw new BlogException(ErrorCode.TOKEN_EXPIRE, "用户名和密码不能为空");
        }
        UserDTO user = userService.login(username, password);

        return ok(user);
    }

    @PostMapping("/addUser")
    public ApiResult addUser(@RequestBody UserDTO userDTO){
        return ok(userService.addUser(userDTO));
    }

    @GetMapping("/getAllUser")
    public ApiResult getAllUser(){
        return ok(userService.getUsers());
    }

    @GetMapping("/getUserByKeyWord")
    public ApiResult getUserByKeyWord(@RequestParam("keyword") String keyword){
        return ok(userService.getUserByKeyWord(keyword));
    }

    @GetMapping("/getUserById")
    public ApiResult getUserById(@RequestParam("id") String id){
        return ok(userService.getUserById(id));
    }

    @PostMapping("/updateUser")
    public ApiResult updateUser(@RequestBody UserDTO userDTO){
        return ok(userService.updateUser(userDTO));
    }

    @GetMapping("/deleteUser")
    public ApiResult deleteUser(@RequestParam("id") String id){
        return ok(userService.deleteUser(id));
    }

    @GetMapping("/changeRole")
    public ApiResult changeRole(@RequestParam("id") String id){
        return ok(userService.changeRole(id));
    }
}
