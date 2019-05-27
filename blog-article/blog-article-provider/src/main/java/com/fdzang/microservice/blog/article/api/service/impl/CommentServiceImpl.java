package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.CommentService;
import com.fdzang.microservice.blog.article.api.utils.ConvertUtils;
import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.dao.domain.*;
import com.fdzang.microservice.blog.article.dao.mapper.ArticleMapper;
import com.fdzang.microservice.blog.article.dao.mapper.CommentMapper;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:01
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<CommentDTO> getCommentByArticleId(String id) {
        CommentDOExample example=new CommentDOExample();
        example.createCriteria().andCommentArticleIdEqualTo(id);
        List<CommentDOWithBLOBs> commentDOS=commentMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(commentDOS)){
            return ConvertUtils.convertCommentList(commentDOS);
        }
        return null;
    }

    @Override
    public List<CommentDTO> getRecentComments() {
        CommentDOExample example=new CommentDOExample();
        example.setOrderByClause(" comment_date desc ");
        List<CommentDOWithBLOBs> commentDOS=commentMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(commentDOS)){
            return ConvertUtils.convertCommentList(commentDOS);
        }
        return null;
    }


    @Override
    public List<CommentDTO> getCommentsByUserEmail(String userEmail) {
        ArticleDOExample example=new ArticleDOExample();
        example.createCriteria().andArticleAuthorEmailEqualTo(userEmail);
        List<ArticleDO> articleDOS=articleMapper.selectByExample(example);

        if(CollectionUtils.isNotEmpty(articleDOS)){
            CommentDOExample commentDOExample=new CommentDOExample();
            commentDOExample.createCriteria().andCommentArticleIdIn(
                    articleDOS.stream().map(ArticleDO::getId).collect(Collectors.toList()));

            List<CommentDOWithBLOBs> commentDOS=commentMapper.selectByExampleWithBLOBs(commentDOExample);
            if(CollectionUtils.isNotEmpty(commentDOS)){
                List<CommentDTO> commentDTOS=new ArrayList<>();
                for (CommentDOWithBLOBs commentDO:commentDOS) {
                    CommentDTO commentDTO=ConvertUtils.convertComment(commentDO);
                    ArticleDO articleDO=articleMapper.selectByPrimaryKey(commentDO.getCommentArticleId());
                    ArticleDTO articleDTO=ConvertUtils.convertArticle(articleDO);
                    commentDTO.setArticle(articleDTO);

                    commentDTOS.add(commentDTO);
                }

                return commentDTOS;
            }
        }

        return null;
    }

    @Override
    public List<CommentDTO> getComments() {
        List<CommentDOWithBLOBs> commentDOS=commentMapper.selectByExampleWithBLOBs(null);
        if(CollectionUtils.isNotEmpty(commentDOS)){
            List<CommentDTO> commentDTOS=new ArrayList<>();
            for (CommentDOWithBLOBs commentDO:commentDOS) {
                CommentDTO commentDTO=ConvertUtils.convertComment(commentDO);
                ArticleDO articleDO=articleMapper.selectByPrimaryKey(commentDO.getCommentArticleId());
                ArticleDTO articleDTO=ConvertUtils.convertArticle(articleDO);
                commentDTO.setArticle(articleDTO);

                commentDTOS.add(commentDTO);
            }

            return commentDTOS;
        }
        return null;
    }

    @Override
    public Boolean deleteComment(String id) {
        int count=commentMapper.deleteByPrimaryKey(id);

        return count>0;
    }

    @Override
    public Boolean deleteArticleComments(String id) {
        CommentDOExample example=new CommentDOExample();
        example.createCriteria().andCommentArticleIdEqualTo(id);
        List<CommentDO> commentDOS=commentMapper.selectByExample(example);
        for (CommentDO comment:commentDOS) {
            commentMapper.deleteByPrimaryKey(comment.getId());
        }

        return true;
    }

    @Override
    public Boolean addComment(CommentDTO comment) {
        ArticleDO articleDO=articleMapper.selectByPrimaryKey(comment.getCommentArticleId());
        articleDO.setArticleCommentCount(articleDO.getArticleCommentCount()+1);
        articleMapper.updateByPrimaryKey(articleDO);

        CommentDOWithBLOBs commentDO=new CommentDOWithBLOBs();
        BeanUtils.copyProperties(comment,commentDO);

        String id = TimeUtils.getTimestamp();
        commentDO.setId(id);
        if(StringUtils.isEmpty(comment.getCommentThumbnailUrl())){
            commentDO.setCommentThumbnailUrl(Constant.Static.DEFAULT_AVATAR);
        }
        commentDO.setCommentDate(new Date());
        commentDO.setCommentSharpUrl(articleDO.getArticlePermalink()+"#"+id);

        int count=commentMapper.insert(commentDO);

        return count>0;
    }

    @Override
    public Boolean replyComment(CommentDTO comment) {
        CommentDOWithBLOBs commentDO=commentMapper.selectByPrimaryKey(
                comment.getCommentOriginalCommentId());
        ArticleDO articleDO=articleMapper.selectByPrimaryKey(commentDO.getCommentArticleId());
        articleDO.setArticleCommentCount(articleDO.getArticleCommentCount()+1);
        articleMapper.updateByPrimaryKey(articleDO);


        CommentDOWithBLOBs comm=new CommentDOWithBLOBs();
        BeanUtils.copyProperties(comment,comm);

        String id = TimeUtils.getTimestamp();
        comm.setId(id);
        if(StringUtils.isEmpty(comment.getCommentThumbnailUrl())){
            comm.setCommentThumbnailUrl(Constant.Static.DEFAULT_AVATAR);
        }
        comm.setCommentDate(new Date());
        comm.setCommentSharpUrl(articleDO.getArticlePermalink()+"#"+id);
        comm.setCommentOriginalCommentName(commentDO.getCommentName());
        comm.setCommentArticleId(articleDO.getId());

        int count=commentMapper.insert(comm);

        return count>0;
    }
}
