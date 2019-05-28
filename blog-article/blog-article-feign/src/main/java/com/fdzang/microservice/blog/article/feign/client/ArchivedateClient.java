package com.fdzang.microservice.blog.article.feign.client;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.feign.client.impl.ArchivedateHystrix;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:45
 */
@FeignClient(value = Constant.ServiceName.BLOG_ARTICLE,fallbackFactory = ArchivedateHystrix.class)
public interface ArchivedateClient {

    @GetMapping("/zuul/archivedate/getArchives")
    ApiResult<List<ArchivedateDTO>> getArchives();

    @GetMapping("/zuul/archivedate/getArchiveByTime")
    ApiResult<ArchivedateDTO> getArchiveByTime(@RequestParam("year")Integer year,
                                               @RequestParam("month")Integer month);

    @GetMapping("/zuul/archivedate/addArticleAndArchive")
    ApiResult<Boolean> addArticleAndArchive(@RequestParam("id")String id,
                                            @RequestParam("isPush") Boolean isPush);


    @GetMapping("/zuul/archivedate/updateArticleAndArchive")
    ApiResult<Boolean> updateArticleAndArchive(@RequestParam("id")String id,
                                               @RequestParam("oldPush") Boolean oldPush,
                                               @RequestParam("newPush") Boolean newPush);

    @GetMapping("/zuul/archivedate/deleteArticleAndArchive")
    ApiResult deleteArticleAndArchive(@RequestParam("id")String id,
                                      @RequestParam("isPush") Boolean isPush);
}
