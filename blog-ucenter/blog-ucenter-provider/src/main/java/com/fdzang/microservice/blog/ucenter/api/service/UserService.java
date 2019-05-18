package com.fdzang.microservice.blog.ucenter.api.service;

import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 9:42
 */
public interface UserService {
    UserDTO getUserByEmail(String email);

    UserDTO login(String email,String password);

    Boolean addUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    List<UserDTO> getUserByKeyWord(String keyword);

    UserDTO getUserById(String id);

    Boolean updateUser(UserDTO userDTO);

    Boolean deleteUser(String id);

    Boolean changeRole(String id);
}
