package com.fdzang.microservice.blog.article.api.service;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:51
 */
public interface ArchivedateService {
    List<ArchivedateDTO> getArchives();

    ArchivedateDTO getArchiveByTime(Integer year,Integer month);

    Boolean addArticleAndArchive(String id,Boolean isPush);
}
