package com.fdzang.microservice.blog.article.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tanghu
 * @Date: 2019/1/8 15:45
 */
@Data
public class CommentDTO {
    private String id;

    private Date commentDate;

    private String commentEmail;

    private String commentName;

    private String commentArticleId;

    private String commentSharpUrl;

    private String commentUrl;

    private String commentOriginalCommentId;

    private String commentOriginalCommentName;

    private String commentContent;

    private String commentThumbnailUrl;

    private boolean replyFlag;

    private ArticleDTO article;

}
