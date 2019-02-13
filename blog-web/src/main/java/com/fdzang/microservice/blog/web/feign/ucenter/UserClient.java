package com.fdzang.microservice.blog.web.feign.ucenter;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.web.feign.ucenter.impl.UserHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:17
 */
@FeignClient(value = Constant.ServiceName.BLOG_UCENTER,fallbackFactory = UserHystrix.class)
public interface UserClient {

    @RequestMapping(value = "/zuul/user/getUserByEmail",method = RequestMethod.GET)
    ApiResult<UserDTO> getUserByEmail(@RequestParam("email") String email);
}
