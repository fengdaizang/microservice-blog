package com.fdzang.microservice.blog.article.common.dto;

import lombok.Data;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:32
 */
@Data
public class TagDTO {
    private String id;

    private Integer tagPublishedRefCount;

    private Integer tagReferenceCount;

    private String tagTitle;
}
