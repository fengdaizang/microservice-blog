package com.fdzang.microservice.blog.console.service.impl;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.console.service.UserService;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDTO login(String email, String password) {
        ApiResult<UserDTO> result = userClient.getUserByEmail(email);
        if(result==null){
            throw new BlogException(ErrorCode.LOGIN_ERROR, "获取用户失败 " + email );
        }
        if(result.getCode() != ErrorCode.SUCCESS){
            throw new BlogException(result.getCode(), result.getMsg());
        }

        UserDTO user=result.getData();
        if (user == null) {
            throw new BlogException(ErrorCode.LOGIN_ERROR, "用户 " + email + " 不存在错误");
        }
        PasswordEncoder pe = new BCryptPasswordEncoder();
        if (!pe.matches(password, user.getPassword())) {
            throw new BlogException(ErrorCode.LOGIN_ERROR, "用户 " + email + " 密码错误");
        }
        user.setPassword(null);

        return user;
    }

}
