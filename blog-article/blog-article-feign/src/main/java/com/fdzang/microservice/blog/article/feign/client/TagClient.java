package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.feign.client.impl.TagHystrix;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:44
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = TagHystrix.class)
public interface TagClient {
    @RequestMapping(value = "/zuul/tag/getMostUsedTags",method = RequestMethod.GET)
    ApiResult getMostUsedTags();

    @RequestMapping(value = "/zuul/tag/getTags",method = RequestMethod.GET)
    ApiResult getTags();

    @RequestMapping(value = "/zuul/tag/getTagByTitle",method = RequestMethod.GET)
    ApiResult getTagByTitle(String title);
}
