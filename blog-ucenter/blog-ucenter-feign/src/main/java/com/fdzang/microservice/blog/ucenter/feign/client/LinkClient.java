package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.ucenter.feign.client.impl.LinkHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:18
 */
@FeignClient(value = "blog-ucenter-v1",fallbackFactory = LinkHystrix.class)
public interface LinkClient {
}
