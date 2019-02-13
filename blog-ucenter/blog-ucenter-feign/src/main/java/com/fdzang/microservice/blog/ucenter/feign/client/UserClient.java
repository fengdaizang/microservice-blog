package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.UserHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:09
 */
@FeignClient(value = "blog-ucenter-v1",fallbackFactory = UserHystrix.class)
public interface UserClient {
    @RequestMapping(value = "/zuul/user/getUserByEmail",method = RequestMethod.GET)
    ApiResult getUserByEmail(@RequestParam("email") String email);
}