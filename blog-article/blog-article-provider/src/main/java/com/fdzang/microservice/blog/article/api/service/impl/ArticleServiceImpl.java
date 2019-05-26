package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.ArticleService;
import com.fdzang.microservice.blog.article.api.utils.ConvertUtils;
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
        ArticleDOExample.Criteria criteria=example.createCriteria();
        criteria.andArticleIsPublishedEqualTo(Constant.Article.PUSH);
        example.setOrderByClause("article_create_date desc");
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andArticleTitleLike("%"+keyword+"%");
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
            List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);
            pageDTO.setResult(articleDTOS);
            pageDTO.setTotalCount(articleDTOS.size());
            return pageDTO;
        }
        return null;
    }

    @Override
    public PageDTO<ArticleDTO> getArticlesByUserEmail(String userEmail, String keyword, Integer pageNo, Integer pageSize) {
        ArticleDOExample example=new ArticleDOExample();
        ArticleDOExample.Criteria criteria=example.createCriteria();
        criteria.andArticleAuthorEmailEqualTo(userEmail)
                .andArticleIsPublishedEqualTo(Constant.Article.PUSH);
        example.setOrderByClause("article_create_date desc");
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andArticleTitleLike("%"+keyword+"%");
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
            List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);
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
            return ConvertUtils.convertArticle(articleDOWithBLOBs.get(0));
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
            List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);

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
            List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);

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
                List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);
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
                List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);
                pageDTO.setResult(articleDTOS);
                pageDTO.setTotalCount(articleDTOS.size());
                return pageDTO;
            }
        }
        return null;
    }

    @Override
    public Boolean addArticle(ArticleDTO article) {
        ArticleDOWithBLOBs articleDO = new ArticleDOWithBLOBs();
        BeanUtils.copyProperties(article,articleDO);

        if(StringUtils.isNotEmpty(article.getArticlePermalink())){
            if(article.getArticlePermalink().startsWith("/")){
                articleDO.setArticlePermalink(article.getArticlePermalink());
            }else{
                articleDO.setArticlePermalink("/"+article.getArticlePermalink());
            }
        }else{
            articleDO.setArticlePermalink(TimeUtils.getArticleLink());
        }
        articleDO.setArticleViewCount(0);
        articleDO.setArticleCommentCount(0);
        articleDO.setArticleCreateDate(new Date());
        articleDO.setArticleCommentable("1");
        articleDO.setArticleEditorType("CodeMirror-Markdown");
        articleDO.setArticleHadBeenPublished(Constant.Article.DRAFT);
        articleDO.setArticlePutTop(Constant.Article.DRAFT);
        articleDO.setArticleSignId("");
        articleDO.setArticleUpdateDate(new Date());
        articleDO.setArticleViewPwd("");
        articleDO.setArticleRandomDouble(5D);

        int count=articleMapper.insert(articleDO);

        return count>0;
    }

    @Override
    public Boolean updateArticle(ArticleDTO article) {
        ArticleDOWithBLOBs articleDO = articleMapper.selectByPrimaryKey(article.getId());

        articleDO.setArticleAbstract(article.getArticleAbstract());
        articleDO.setArticleTitle(article.getArticleTitle());
        if(StringUtils.isNotEmpty(article.getArticlePermalink())){
            articleDO.setArticlePermalink(article.getArticlePermalink());
        }
        articleDO.setArticleTags(article.getArticleTags());
        articleDO.setArticleIsPublished(article.getArticleIsPublished());
        articleDO.setArticleContent(article.getArticleContent());
        articleDO.setArticleUpdateDate(new Date());
        articleDO.setArticleHadBeenPublished(Constant.Article.PUSH);

        int count = articleMapper.updateByPrimaryKey(articleDO);

        return count>0;
    }

    @Override
    public Boolean pushTop(String id, String isTop) {
        ArticleDOWithBLOBs articleDO = articleMapper.selectByPrimaryKey(id);
        articleDO.setArticlePutTop(isTop);
        int count = articleMapper.updateByPrimaryKey(articleDO);

        return count>0;
    }

    @Override
    public Boolean deleteArticle(String id) {
        int count = articleMapper.deleteByPrimaryKey(id);

        return count>0;
    }


    @Override
    public PageDTO<ArticleDTO> getDrafts(String keyword, Integer pageNo, Integer pageSize) {
        ArticleDOExample example=new ArticleDOExample();
        ArticleDOExample.Criteria criteria=example.createCriteria();
        criteria.andArticleIsPublishedEqualTo(Constant.Article.DRAFT);
        example.setOrderByClause("article_create_date desc");
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andArticleTitleLike("%"+keyword+"%");
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
            List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);
            pageDTO.setResult(articleDTOS);
            pageDTO.setTotalCount(articleDTOS.size());
            return pageDTO;
        }
        return null;
    }

    @Override
    public PageDTO<ArticleDTO> getDraftsByUserEmail(String userEmail, String keyword, Integer pageNo, Integer pageSize) {
        ArticleDOExample example=new ArticleDOExample();
        ArticleDOExample.Criteria criteria=example.createCriteria();
        criteria.andArticleAuthorEmailEqualTo(userEmail)
                .andArticleIsPublishedEqualTo(Constant.Article.DRAFT);
        example.setOrderByClause("article_create_date desc");
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andArticleTitleLike("%"+keyword+"%");
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
            List<ArticleDTO> articleDTOS=ConvertUtils.convertArticleList(articleDOWithBLOBs);
            pageDTO.setResult(articleDTOS);
            pageDTO.setTotalCount(articleDTOS.size());
            return pageDTO;
        }
        return null;
    }
}
