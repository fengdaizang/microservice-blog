package com.fdzang.microservice.blog.article.feign.client.impl;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.feign.client.ArchivedateClient;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:46
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

            @Override
            public ApiResult<Boolean> addArticleAndArchive(String id, Boolean isPush) {
                return null;
            }
        };
    }
}