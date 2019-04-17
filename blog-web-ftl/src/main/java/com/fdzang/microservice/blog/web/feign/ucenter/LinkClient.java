package com.fdzang.microservice.blog.web.feign.ucenter;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.web.feign.ucenter.impl.LinkHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:26
 */
@FeignClient(value = Constant.ServiceName.BLOG_UCENTER,fallbackFactory = LinkHystrix.class)
public interface LinkClient {

    @RequestMapping(value = "/zuul/link/getLinks",method = RequestMethod.GET)
    ApiResult<List<LinkDTO>> getLinks();

}
