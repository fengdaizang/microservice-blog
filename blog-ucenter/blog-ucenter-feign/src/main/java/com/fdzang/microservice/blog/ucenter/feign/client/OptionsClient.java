package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.UserHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:11
 */
@FeignClient(value = "blog-ucenter-v1",fallbackFactory = UserHystrix.class)
public interface OptionsClient {
    @RequestMapping(value = "/zuul/options/getAllOptions",method = RequestMethod.GET)
    ApiResult getAllOptions();

    @RequestMapping(value = "/zuul/options/getOptionById",method = RequestMethod.GET)
    ApiResult getOptionById(@RequestParam("id") String id);

    @RequestMapping(value = "/zuul/options/updateOption",method = RequestMethod.GET)
    ApiResult updateOption(@RequestBody OptionsDTO option);
}
