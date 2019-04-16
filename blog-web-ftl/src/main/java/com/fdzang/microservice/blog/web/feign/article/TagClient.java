package com.fdzang.microservice.blog.web.feign.article;

import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.impl.TagHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:17
 */
@FeignClient(value = Constant.ServiceName.BLOG_ARTICLE,fallbackFactory = TagHystrix.class)
public interface TagClient {
    @RequestMapping(value = "/zuul/tag/getMostUsedTags",method = RequestMethod.GET)
    ApiResult<List<TagDTO>> getMostUsedTags();

    @RequestMapping(value = "/zuul/tag/getTags",method = RequestMethod.GET)
    ApiResult<List<TagDTO>> getTags();

    @RequestMapping(value = "/zuul/tag/getTagByTitle",method = RequestMethod.GET)
    ApiResult<TagDTO> getTagByTitle(@RequestParam("title") String title);


}
