package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.UserHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:09
 */
@FeignClient(value = Constant.ServiceName.BLOG_UCENTER,fallbackFactory = UserHystrix.class)
public interface UserClient {
    @GetMapping(value = "/zuul/user/getUserByEmail")
    ApiResult<UserDTO> getUserByEmail(@RequestParam("email") String email);

    @GetMapping(value = "/zuul/user/userLogin")
    ApiResult<UserDTO> userLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password) ;

    @PostMapping(value = "/zuul/user/addUser")
    ApiResult<Boolean> addUser(@RequestBody UserDTO userDTO);

    @GetMapping(value = "/zuul/user/getAllUser")
    ApiResult<List<UserDTO>> getAllUser();

    @GetMapping(value = "/zuul/user/getUserByKeyWord")
    ApiResult<List<UserDTO>> getUserByKeyWord(@RequestParam("keyword") String keyword);

    @GetMapping(value = "/zuul/user/getUserById")
    ApiResult<UserDTO> getUserById(@RequestParam("id") String id);

    @PostMapping(value = "/zuul/user/updateUser")
    ApiResult<Boolean> updateUser(@RequestBody UserDTO userDTO);

    @GetMapping(value = "/zuul/user/deleteUser")
    ApiResult<Boolean> deleteUser(@RequestParam("id") String id);

    @GetMapping(value = "/zuul/user/changeRole")
    ApiResult<Boolean> changeRole(@RequestParam("id") String id);
}