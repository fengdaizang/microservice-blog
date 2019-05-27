package com.fdzang.microservice.blog.article.api.service;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:51
 */
public interface CommentService {
    List<CommentDTO> getCommentByArticleId(String id);

    List<CommentDTO> getRecentComments();

    List<CommentDTO> getCommentsByUserEmail(String userEmail);

    List<CommentDTO> getComments();

    Boolean deleteComment(String id);

    Boolean deleteArticleComments(String id);

    Boolean addComment(CommentDTO comment);

    Boolean replyComment(CommentDTO comment);

}
