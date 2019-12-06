package com.fdzang.microservice.blog.ucenter.api.service.impl;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.api.service.UserService;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.dao.domain.UserDO;
import com.fdzang.microservice.blog.ucenter.dao.domain.UserDOExample;
import com.fdzang.microservice.blog.ucenter.dao.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        userDO.setUserEmail(userDTO.getUserEmail());
        userDO.setUserName(userDTO.getUserName());
        userDO.setUserPassword(pe.encode(userDTO.getPassword()));
        userDO.setUserPublishedArticleCount(0);
        userDO.setUserRole(Constant.UserRole.VISITOR);
        userDO.setUserUrl(userDTO.getUserUrl());
        if(StringUtils.isNotEmpty(userDTO.getUserAvatar())){
            userDO.setUserAvatar(userDTO.getUserAvatar());
        }else{
            userDO.setUserAvatar(Constant.Static.DEFAULT_AVATAR);
        }

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

    @Override
    public List<UserDTO> getUsers() {
        List<UserDO> userDOS=userMapper.selectByExample(null);
        if(CollectionUtils.isNotEmpty(userDOS)){
            List<UserDTO> userDTOS=new ArrayList<>();
            for (UserDO user:userDOS) {
                UserDTO userDTO=new UserDTO();
                BeanUtils.copyProperties(user,userDTO);

                userDTOS.add(userDTO);
            }
            return userDTOS;
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserByKeyWord(String keyword) {
        UserDOExample example=new UserDOExample();
        UserDOExample.Criteria criteria1=new UserDOExample().createCriteria();
        example.createCriteria().andUserNameLike("%"+keyword+"%");
        criteria1.andUserEmailLike("%"+keyword+"%");
        example.or(criteria1);

        List<UserDO> userDOS=userMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(userDOS)){
            List<UserDTO> userDTOS=new ArrayList<>();
            for (UserDO user:userDOS) {
                UserDTO userDTO=new UserDTO();
                BeanUtils.copyProperties(user,userDTO);

                userDTOS.add(userDTO);
            }
            return userDTOS;
        }
        return null;
    }

    @Override
    public UserDTO getUserById(String id) {
        UserDO user=userMapper.selectByPrimaryKey(id);

        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        return userDTO;
    }

    @Override
    public Boolean updateUser(UserDTO userDTO) {
        UserDO user=userMapper.selectByPrimaryKey(userDTO.getId());

        user.setUserName(userDTO.getUserName());
        user.setUserUrl(userDTO.getUserUrl());
        user.setUserAvatar(user.getUserAvatar());

        PasswordEncoder pe = new BCryptPasswordEncoder();
        if (StringUtils.isNotEmpty(userDTO.getPassword())) {
            user.setUserPassword(pe.encode(userDTO.getPassword()));
        }

        int count=userMapper.updateByPrimaryKeySelective(user);
        return count>0;
    }

    @Override
    public Boolean deleteUser(String id) {
        int count=userMapper.deleteByPrimaryKey(id);
        return count>0;
    }

    @Override
    public Boolean changeRole(String id) {
        UserDO user=userMapper.selectByPrimaryKey(id);
        if (Constant.UserRole.VISITOR.equals(user.getUserRole())){
            user.setUserRole(Constant.UserRole.DEFAULT);
        }else{
            user.setUserRole(Constant.UserRole.VISITOR);
        }

        int count=userMapper.updateByPrimaryKeySelective(user);
        return count>0;
    }
}
