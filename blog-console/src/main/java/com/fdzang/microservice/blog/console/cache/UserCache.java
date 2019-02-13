package com.fdzang.microservice.blog.console.cache;

import com.alibaba.fastjson.JSON;
import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.UserClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author tanghu
 * @Date: 2019/1/21 9:33
 */

@Component
public class UserCache {
    @Autowired
    private UserClient userClient;

    @Autowired
    private StringRedisTemplate template;

    private static String namespace = "V1:BlogUser:Token:";
    private static int tokenSize = 8;

    public String generateToken(UserDTO user) {
        long time = System.currentTimeMillis();
        String token = DigestUtils.md5Hex(RandomStringUtils.randomAlphanumeric(tokenSize) + user.getUserName() + time);
        template.opsForValue().set(namespace + token, JSON.toJSONString(user), Constant.Cache.CACHE_ONE_DAY,
                TimeUnit.SECONDS);
        return token;
    }

    public UserDTO checkToken(String token) {
        String userCache = template.opsForValue().get(namespace + token);
        if (StringUtils.isEmpty(userCache)) {
            throw new BlogException(ErrorCode.TOKEN_EXPIRE, "BlogUserToken " + token + " is expired");
        }
        UserDTO user = JSON.parseObject(userCache, UserDTO.class);
        return user;
    }

    public void expired(String token) {
        template.delete(namespace + token);
    }

    public void refreshToken(String token, String username) {
        ApiResult<UserDTO> g7Result = userClient.getUserByEmail(username);
        if(g7Result.getData()!=null&&g7Result.getData()!=null){
            template.opsForValue().set(namespace + token, JSON.toJSONString(g7Result.getData()), Constant.Cache.CACHE_ONE_DAY,
                    TimeUnit.SECONDS);
        }
    }


}
