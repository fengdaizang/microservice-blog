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
}
