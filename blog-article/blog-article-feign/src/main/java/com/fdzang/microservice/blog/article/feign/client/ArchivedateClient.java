package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.feign.client.impl.ArchivedateHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:45
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = ArchivedateHystrix.class)
public interface ArchivedateClient {
}
