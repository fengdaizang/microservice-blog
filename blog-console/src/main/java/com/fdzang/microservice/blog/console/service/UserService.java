package com.fdzang.microservice.blog.console.service;

import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:31
 */
public interface UserService {
    UserDTO login(String email,String password);
}
