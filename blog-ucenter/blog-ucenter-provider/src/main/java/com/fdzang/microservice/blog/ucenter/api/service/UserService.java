package com.fdzang.microservice.blog.ucenter.api.service;

import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;

/**
 * @author tanghu
 * @Date: 2019/1/8 9:42
 */
public interface UserService {
    UserDTO getUserByEmail(String email);
}
