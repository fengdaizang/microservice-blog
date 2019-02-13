package com.fdzang.microservice.blog.web.feign.article.impl;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.web.feign.article.ArchivedateClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:18
 */
@Component
@Slf4j
public class ArchivedateHystrix implements FallbackFactory<ArchivedateClient> {

    @Override
    public ArchivedateClient create(Throwable throwable) {
        return new ArchivedateClient() {
            @Override
            public ApiResult<List<ArchivedateDTO>> getArchives() {
                return null;
            }

            @Override
            public ApiResult<ArchivedateDTO> getArchiveByTime(Integer year, Integer month) {
                return null;
            }
        };
    }

}
