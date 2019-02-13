package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.ArticleService;
import com.fdzang.microservice.blog.article.api.utils.MarkDown2HtmlUtils;
import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.dao.domain.*;
import com.fdzang.microservice.blog.article.dao.mapper.ArchivedateArticleMapper;
import com.fdzang.microservice.blog.article.dao.mapper.ArticleMapper;
import com.fdzang.microservice.blog.article.dao.mapper.TagArticleMapper;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanghu
 * @Date: 2019/1/8 11:08
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Autowired
    private ArchivedateArticleMapper archivedateArticleMapper;

    @Override
    public PageDTO<ArticleDTO> getArticles(String keyword, Integer pageNo, Integer pageSize) {
        ArticleDOExample example=new ArticleDOExample();
        example.setOrderByClause("article_create_date desc");
        if(StringUtils.isNotEmpty(keyword)){
            example.createCriteria().andArticleTitleLike("%"+keyword+"%");
        }
        long count=articleMapper.countByExample(example);
        if(count<1){
            throw new BlogException(ErrorCode.DATA_NULL_ERROR,"未查询到数据，请重新输入条件！");
        }

        if(pageNo==null){
            pageNo=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        PageDTO<ArticleDTO> pageDTO=new PageDTO<>(pageNo,pageSize,count);
        int total_page=pageDTO.getTotalPage();
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>total_page){
            pageNo=total_page;
        }
        pageDTO.setPageNo(pageNo);
        pageDTO.setPageSize(pageSize);

        example.setStartPos(pageDTO.getStartPos());
        example.setPageSize(pageSize);
        List<ArticleDOWithBLOBs> articleDOWithBLOBs=articleMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(articleDOWithBLOBs)){
            List<ArticleDTO> articleDTOS=new ArrayList<>();
            for (ArticleDOWithBLOBs article: articleDOWithBLOBs) {
                ArticleDTO articleDTO=new ArticleDTO();
                BeanUtils.copyProperties(article,articleDTO);
                articleDTO.setArticleAbstract(
                        MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleAbstract()));
                articleDTOS.add(articleDTO);
            }
            pageDTO.setResult(articleDTOS);
            pageDTO.setTotalCount(articleDTOS.size());
            return pageDTO;
        }
        return null;
    }

    @Override
    public ArticleDTO getArticleByPermalink(String permalink) {
        ArticleDOExample example=new ArticleDOExample();
        example.createCriteria().andArticlePermalinkEqualTo(permalink);

        List<ArticleDOWithBLOBs> articleDOWithBLOBs=articleMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(articleDOWithBLOBs)){
            ArticleDTO articleDTO=new ArticleDTO();
            BeanUtils.copyProperties(articleDOWithBLOBs.get(0),articleDTO);
            articleDTO.setArticleAbstract(
                    MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleAbstract()));
            return articleDTO;
        }
        return null;
    }

    @Override
    public Boolean addArticleViewCount(String id) {
        ArticleDO articleDO=articleMapper.selectByPrimaryKey(id);
        articleDO.setArticleViewCount(articleDO.getArticleViewCount()+1);
        return articleMapper.updateByPrimaryKey(articleDO)>0;
    }

    @Override
    public List<ArticleDTO> getMostCommentArticles() {
        ArticleDOExample example=new ArticleDOExample();
        example.setOrderByClause("article_comment_count desc");
        example.setStartPos(0);
        example.setPageSize(Constant.Page.PAGESIZE);
        List<ArticleDOWithBLOBs> articleDOWithBLOBs=articleMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(articleDOWithBLOBs)){
            List<ArticleDTO> articleDTOS=new ArrayList<>();
            for (ArticleDOWithBLOBs article: articleDOWithBLOBs) {
                ArticleDTO articleDTO=new ArticleDTO();
                BeanUtils.copyProperties(article,articleDTO);
                articleDTOS.add(articleDTO);
            }

            return articleDTOS;
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getMostViewCountArticles() {
        ArticleDOExample example=new ArticleDOExample();
        example.setOrderByClause("article_view_count desc");
        example.setStartPos(0);
        example.setPageSize(Constant.Page.PAGESIZE);
        List<ArticleDOWithBLOBs> articleDOWithBLOBs=articleMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(articleDOWithBLOBs)){
            List<ArticleDTO> articleDTOS=new ArrayList<>();
            for (ArticleDOWithBLOBs article: articleDOWithBLOBs) {
                ArticleDTO articleDTO=new ArticleDTO();
                BeanUtils.copyProperties(article,articleDTO);
                articleDTOS.add(articleDTO);
            }

            return articleDTOS;
        }
        return null;
    }

    @Override
    public PageDTO<ArticleDTO> getArticlesByTagId(String tagId, Integer pageNo, Integer pageSize) {
        TagArticleDOExample example=new TagArticleDOExample();
        example.createCriteria().andTagIdEqualTo(tagId);

        List<TagArticleDO> tagArticleDOS=tagArticleMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagArticleDOS)){
            ArticleDOExample example1=new ArticleDOExample();
            example1.setOrderByClause("article_create_date desc");
            example1.createCriteria().andIdIn(
                    tagArticleDOS.stream().map(TagArticleDO::getArticleId).collect(Collectors.toList()));
            long count=articleMapper.countByExample(example1);
            if(count<1){
                throw new BlogException(ErrorCode.DATA_NULL_ERROR,"未查询到数据，请重新输入条件！");
            }

            if(pageNo==null){
                pageNo=1;
            }
            if(pageSize==null){
                pageSize=10;
            }
            PageDTO<ArticleDTO> pageDTO=new PageDTO<>(pageNo,pageSize,count);
            int total_page=pageDTO.getTotalPage();
            if(pageNo<1){
                pageNo=1;
            }
            if(pageNo>total_page){
                pageNo=total_page;
            }
            pageDTO.setPageNo(pageNo);
            pageDTO.setPageSize(pageSize);

            example1.setStartPos(pageDTO.getStartPos());
            example1.setPageSize(pageSize);
            List<ArticleDOWithBLOBs> articleDOWithBLOBs=articleMapper.selectByExampleWithBLOBs(example1);
            if(CollectionUtils.isNotEmpty(articleDOWithBLOBs)) {
                List<ArticleDTO> articleDTOS = new ArrayList<>();
                for (ArticleDOWithBLOBs article : articleDOWithBLOBs) {
                    ArticleDTO articleDTO = new ArticleDTO();
                    BeanUtils.copyProperties(article, articleDTO);
                    articleDTO.setArticleAbstract(
                            MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleAbstract()));
                    articleDTOS.add(articleDTO);
                }
                pageDTO.setResult(articleDTOS);
                pageDTO.setTotalCount(articleDTOS.size());
                return pageDTO;
            }
        }
        return null;
    }

    @Override
    public ArticleDTO getArticleById(String id) {
        ArticleDOWithBLOBs articleDOWithBLOBs=articleMapper.selectByPrimaryKey(id);

        ArticleDTO articleDTO=new ArticleDTO();
        BeanUtils.copyProperties(articleDOWithBLOBs,articleDTO);
        articleDTO.setArticleAbstract(
                MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleAbstract()));

        return articleDTO;
    }

    @Override
    public PageDTO<ArticleDTO> getArticlesByArchiveId(String archiveId, Integer pageNo, Integer pageSize) {
        ArchivedateArticleDOExample example=new ArchivedateArticleDOExample();
        example.createCriteria().andArchivedateIdEqualTo(archiveId);
        List<ArchivedateArticleDO> archivedateArticleDOS=archivedateArticleMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(archivedateArticleDOS)){
            ArticleDOExample example1=new ArticleDOExample();
            example1.setOrderByClause("article_create_date desc");
            example1.createCriteria().andIdIn(
                    archivedateArticleDOS.stream().map(ArchivedateArticleDO::getArticleId).collect(Collectors.toList()));
            long count=articleMapper.countByExample(example1);
            if(count<1){
                throw new BlogException(ErrorCode.DATA_NULL_ERROR,"未查询到数据，请重新输入条件！");
            }

            if(pageNo==null){
                pageNo=1;
            }
            if(pageSize==null){
                pageSize=10;
            }
            PageDTO<ArticleDTO> pageDTO=new PageDTO<>(pageNo,pageSize,count);
            int total_page=pageDTO.getTotalPage();
            if(pageNo<1){
                pageNo=1;
            }
            if(pageNo>total_page){
                pageNo=total_page;
            }
            pageDTO.setPageNo(pageNo);
            pageDTO.setPageSize(pageSize);

            example1.setStartPos(pageDTO.getStartPos());
            example1.setPageSize(pageSize);
            List<ArticleDOWithBLOBs> articleDOWithBLOBs=articleMapper.selectByExampleWithBLOBs(example1);
            if(CollectionUtils.isNotEmpty(articleDOWithBLOBs)){
                List<ArticleDTO> articleDTOS=new ArrayList<>();
                for (ArticleDOWithBLOBs article: articleDOWithBLOBs) {
                    ArticleDTO articleDTO=new ArticleDTO();
                    BeanUtils.copyProperties(article,articleDTO);
                    articleDTO.setArticleAbstract(
                            MarkDown2HtmlUtils.markdown2Html(articleDTO.getArticleAbstract()));
                    articleDTOS.add(articleDTO);
                }
                pageDTO.setResult(articleDTOS);
                pageDTO.setTotalCount(articleDTOS.size());
                return pageDTO;
            }
        }
        return null;
    }
}
