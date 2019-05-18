package com.fdzang.microservice.blog.ucenter.api.service.impl;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.api.service.UserService;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.dao.domain.UserDO;
import com.fdzang.microservice.blog.ucenter.dao.domain.UserDOExample;
import com.fdzang.microservice.blog.ucenter.dao.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 9:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDOExample example=new UserDOExample();
        example.createCriteria().andUserEmailEqualTo(email);
        List<UserDO> userDOS=userMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(userDOS)){
            UserDO userDO=userDOS.get(0);
            UserDTO userDTO=new UserDTO();
            BeanUtils.copyProperties(userDO,userDTO);
            userDTO.setPassword(userDO.getUserPassword());

            return userDTO;
        }
        return null;
    }

    @Override
    public Boolean addUser(UserDTO userDTO) {
        UserDTO user=getUserByEmail(userDTO.getUserEmail());
        if (user != null) {
            throw new BlogException(ErrorCode.LOGIN_ERROR, "用户 " + userDTO.getUserEmail() + " 已存在错误");
        }

        PasswordEncoder pe = new BCryptPasswordEncoder();

        UserDO userDO=new UserDO();
        userDO.setId(System.currentTimeMillis()+"");
        userDO.setUserArticleCount(0);
        userDO.setUserAvatar(Constant.Static.DEFAULT_AVATAR);
        userDO.setUserEmail(userDTO.getUserEmail());
        userDO.setUserName(userDTO.getUserName());
        userDO.setUserPassword(pe.encode(userDTO.getPassword()));
        userDO.setUserPublishedArticleCount(0);
        userDO.setUserRole(Constant.UserRole.VISITOR);
        userDO.setUserUrl(userDTO.getUserUrl());

        int count=userMapper.insert(userDO);

        return count>0;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO user=getUserByEmail(email);
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
