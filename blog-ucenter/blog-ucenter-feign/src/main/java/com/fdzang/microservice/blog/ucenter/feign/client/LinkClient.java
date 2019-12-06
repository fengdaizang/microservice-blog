package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.LinkHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:18
 */
@FeignClient(value = Constant.ServiceName.BLOG_UCENTER,fallbackFactory = LinkHystrix.class)
public interface LinkClient {
    @GetMapping(value = "/zuul/link/getLinks")
    ApiResult<List<LinkDTO>> getLinks();

    @GetMapping(value = "/zuul/link/getLinksByKeyword")
    ApiResult<List<LinkDTO>> getLinksByKeyword(@RequestParam("keyword") String keyword);

    @PostMapping(value = "/zuul/link/addLink")
    ApiResult<Boolean> addLink(@RequestBody LinkDTO link);

    @GetMapping(value = "/zuul/link/getLinksById")
    ApiResult<LinkDTO> getLinksById(@RequestParam("id")String id);

    @PostMapping(value = "/zuul/link/updateLink")
    ApiResult<Boolean> updateLink(@RequestBody LinkDTO link);

    @GetMapping(value = "/zuul/link/deleteLink")
    ApiResult<Boolean> deleteLink(@RequestParam("id")String id);
}
