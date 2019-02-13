package com.fdzang.microservice.blog.article.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author tanghu
 * @Date: 2019/1/8 15:02
 */
@Data
public class ArticleDTO {
    private String id;

    private String articleTitle;

    private String articleAuthorEmail;

    private Integer articleCommentCount;

    private Integer articleViewCount;

    private String articlePermalink;

    private String articleHadBeenPublished;

    private String articleIsPublished;

    private String articlePutTop;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleCreateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleUpdateDate;

    private Double articleRandomDouble;

    private String articleSignId;

    private String articleCommentable;

    private String articleViewPwd;

    private String articleEditorType;

    private String articleAbstract;

    private String articleTags;

    private String articleContent;

    private boolean hasUpdated;

}
