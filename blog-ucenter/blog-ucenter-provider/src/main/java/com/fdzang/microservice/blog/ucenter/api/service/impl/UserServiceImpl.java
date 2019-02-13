package com.fdzang.microservice.blog.ucenter.api.service.impl;

import com.fdzang.microservice.blog.ucenter.api.service.UserService;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.dao.domain.UserDO;
import com.fdzang.microservice.blog.ucenter.dao.domain.UserDOExample;
import com.fdzang.microservice.blog.ucenter.dao.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
            UserDTO userDTO=new UserDTO();
            BeanUtils.copyProperties(userDOS.get(0),userDTO);

            return userDTO;
        }
        return null;
    }
}
