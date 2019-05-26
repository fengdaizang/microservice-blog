package com.fdzang.microservice.blog.article.api.service;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.common.entity.PageDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 11:08
 */
public interface ArticleService {
    PageDTO<ArticleDTO> getArticles(String keyword, Integer pageNo, Integer pageSize);

    PageDTO<ArticleDTO> getArticlesByUserEmail(String userEmail, String keyword, Integer pageNo, Integer pageSize);

    ArticleDTO getArticleByPermalink(String permalink);

    Boolean addArticleViewCount(String id);

    List<ArticleDTO> getMostCommentArticles();

    List<ArticleDTO> getMostViewCountArticles();

    PageDTO<ArticleDTO> getArticlesByTagId(String tagId, Integer pageNo, Integer pageSize);

    PageDTO<ArticleDTO> getArticlesByArchiveId(String archiveId, Integer pageNo, Integer pageSize);

    ArticleDTO getArticleById(String id);

    Boolean addArticle(ArticleDTO article);

    Boolean updateArticle(ArticleDTO article);

    Boolean pushTop(String id, String isTop);

    Boolean deleteArticle(String id);

    PageDTO<ArticleDTO> getDrafts(String keyword, Integer pageNo, Integer pageSize);

    PageDTO<ArticleDTO> getDraftsByUserEmail(String userEmail, String keyword, Integer pageNo, Integer pageSize);
}
