package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.LinkHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:18
 */
@FeignClient(value = "blog-ucenter-v1",fallbackFactory = LinkHystrix.class)
public interface LinkClient {
    @RequestMapping(value = "/zuul/options/getOptionById",method = RequestMethod.GET)
    ApiResult<List<LinkDTO>> getLinks();
}
