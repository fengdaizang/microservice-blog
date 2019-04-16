package com.fdzang.microservice.blog.web.feign.console;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.web.feign.console.impl.ConsoleUserHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:17
 */
@FeignClient(value = Constant.ServiceName.BLOG_CONSOLE,fallbackFactory = ConsoleUserHystrix.class)
public interface ConsoleUserClient {
    @RequestMapping(value = "/zuul/user/login",method = RequestMethod.POST)
    ApiResult<UserDTO> userLogin(@RequestParam("username") String username, @RequestParam("password") String password);

}
