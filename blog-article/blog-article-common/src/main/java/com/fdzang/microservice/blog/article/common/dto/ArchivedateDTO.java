package com.fdzang.microservice.blog.article.common.dto;

import lombok.Data;

/**
 * @author tanghu
 * @Date: 2019/1/8 15:39
 */
@Data
public class ArchivedateDTO {
    private String id;

    private Integer archivedeteArticleCount;

    private Integer archivedatePublishedArticleCount;

    private Long archiveTime;

    private String monthName;

    private Integer archiveDateYear;

    private Integer archiveDateMonth;
}
