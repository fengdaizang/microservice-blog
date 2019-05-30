package com.fdzang.microservice.blog.article.api.utils;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.dao.domain.*;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/5/25 9:17
 */
public class ConvertUtils {
    public static TagDTO convertTag(TagDO tagDO){
        TagDTO tagDTO=new TagDTO();
        BeanUtils.copyProperties(tagDO,tagDTO);

        return tagDTO;
    }

    public static List<TagDTO> convertTagList(List<TagDO> tagDOS){
        List<TagDTO> tagDTOS=new ArrayList<>();
        for (TagDO tag:tagDOS) {
            tagDTOS.add(convertTag(tag));
        }

        return tagDTOS;
    }

    public static CommentDTO convertComment(CommentDOWithBLOBs commentDO){
        CommentDTO commentDTO=new CommentDTO();
        BeanUtils.copyProperties(commentDO,commentDTO);
        if(StringUtils.isNotEmpty(commentDO.getCommentOriginalCommentId())){
            commentDTO.setReplyFlag(true);
        }else{
            commentDTO.setReplyFlag(false);
        }

        return commentDTO;
    }

    public static List<CommentDTO> convertCommentList(List<CommentDOWithBLOBs> commentDOS){
        List<CommentDTO> commentDTOS=new ArrayList<>();
        for (CommentDOWithBLOBs comment:commentDOS) {
            commentDTOS.add(convertComment(comment));
        }

        return commentDTOS;
    }

    public static ArticleDTO convertArticle(ArticleDO articleDO){
        ArticleDTO articleDTO=new ArticleDTO();
        BeanUtils.copyProperties(articleDO,articleDTO);
        return articleDTO;
    }

    public static ArticleDTO convertArticle(ArticleDOWithBLOBs articleDO){
        ArticleDTO articleDTO=new ArticleDTO();
        BeanUtils.copyProperties(articleDO,articleDTO);
        articleDTO.setArticleAbstract(
                MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleAbstract()));
        articleDTO.setArticleContent(
                MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleContent()));
        return articleDTO;
    }

    public static List<ArticleDTO> convertArticleList(List<ArticleDOWithBLOBs> articleDOS){
        List<ArticleDTO> articleDTOS=new ArrayList<>();
        for (ArticleDOWithBLOBs article: articleDOS) {
            articleDTOS.add(convertArticle(article));
        }

        return articleDTOS;
    }

    public static ArchivedateDTO convertArchivedate(ArchivedateDO archivedateDO){
        ArchivedateDTO archivedateDTO=new ArchivedateDTO();
        BeanUtils.copyProperties(archivedateDO,archivedateDTO);
        LocalDate localDate= TimeUtils.getByTimeStamp(archivedateDTO.getArchiveTime());
        archivedateDTO.setArchiveDateYear(localDate.getYear());
        archivedateDTO.setArchiveDateMonth(localDate.getMonthValue());
        archivedateDTO.setMonthName(localDate.getMonth().name());

        return archivedateDTO;
    }

    public static List<ArchivedateDTO> convertArchivedateList(List<ArchivedateDO> archivedateDOS){
        List<ArchivedateDTO> archivedateDTOS=new ArrayList<>();
        for (ArchivedateDO archivedateDO:archivedateDOS) {
            archivedateDTOS.add(convertArchivedate(archivedateDO));
        }
        return archivedateDTOS;
    }
}
