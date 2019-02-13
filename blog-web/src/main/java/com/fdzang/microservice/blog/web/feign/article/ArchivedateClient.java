package com.fdzang.microservice.blog.web.feign.article;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.impl.ArchivedateHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/21 14:18
 */
@FeignClient(value = Constant.ServiceName.BLOG_ARTICLE,fallbackFactory = ArchivedateHystrix.class)
public interface ArchivedateClient {

    @RequestMapping(value = "/zuul/archivedate/getArchives",method = RequestMethod.GET)
    ApiResult<List<ArchivedateDTO>> getArchives();

    @RequestMapping(value = "/zuul/archivedate/getArchiveByTime",method = RequestMethod.GET)
    ApiResult<ArchivedateDTO> getArchiveByTime(@RequestParam("year") Integer year,
                                                     @RequestParam("month") Integer month);
}
