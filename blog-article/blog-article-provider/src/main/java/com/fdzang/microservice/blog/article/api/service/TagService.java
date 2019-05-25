package com.fdzang.microservice.blog.article.api.service;

import com.fdzang.microservice.blog.article.common.dto.TagDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:51
 */
public interface TagService {
    List<TagDTO> getMostUsedTags();

    List<TagDTO> getTags();

    TagDTO getTagByTitle(String title);

    Boolean addArticleAndTag(String tags,String id, Boolean isPush);
}
