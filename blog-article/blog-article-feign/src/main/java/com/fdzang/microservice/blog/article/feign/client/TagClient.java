package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.feign.client.impl.TagHystrix;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:44
 */
@FeignClient(value = "blog-article-v1",fallbackFactory = TagHystrix.class)
public interface TagClient {
    @GetMapping("/zuul/tag/getMostUsedTags")
    ApiResult<List<TagDTO>> getMostUsedTags();

    @GetMapping("/zuul/tag/getTags")
    ApiResult<List<TagDTO>> getTags();

    @GetMapping("/zuul/tag/getTagByTitle")
    ApiResult<TagDTO> getTagByTitle(String title);

    @GetMapping("/zuul/tag/addArticleAndTag")
    ApiResult<Boolean> addArticleAndTag(@RequestParam("tags") String tags,
                                        @RequestParam("id") String id,
                                        @RequestParam("isPush") Boolean isPush);

    @GetMapping("/zuul/tag/updateArticleAndTag")
    ApiResult<Boolean> updateArticleAndTag(@RequestParam("tags") String tags,
                                           @RequestParam("id") String id,
                                           @RequestParam("oldPush") Boolean oldPush,
                                           @RequestParam("newPush") Boolean newPush);

    @GetMapping("/zuul/tag/deleteArticleAndTag")
    ApiResult<Boolean> deleteArticleAndTag(@RequestParam("id") String id,
                        @RequestParam("isPush") Boolean isPush);
}
