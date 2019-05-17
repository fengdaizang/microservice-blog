package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.UserHystrix;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:09
 */
@FeignClient(value = "blog-ucenter-v1",fallbackFactory = UserHystrix.class)
public interface UserClient {
    @GetMapping(value = "/zuul/user/getUserByEmail")
    ApiResult<UserDTO> getUserByEmail(@RequestParam("email") String email);

    @GetMapping(value = "/zuul/user/userLogin")
    ApiResult<UserDTO> userLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password) ;

    @PostMapping(value = "/zuul/user/addUser")
    ApiResult<Boolean> addUser(@RequestBody UserDTO userDTO);
}