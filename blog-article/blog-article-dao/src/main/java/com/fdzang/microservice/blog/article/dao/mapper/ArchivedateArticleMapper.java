package com.fdzang.microservice.blog.article.dao.mapper;

import com.fdzang.microservice.blog.article.dao.domain.ArchivedateArticleDO;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateArticleDOExample;
import java.util.List;

public interface ArchivedateArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    int insert(ArchivedateArticleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    int insertSelective(ArchivedateArticleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    List<ArchivedateArticleDO> selectByExample(ArchivedateArticleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    ArchivedateArticleDO selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ArchivedateArticleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table archivedate_article
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ArchivedateArticleDO record);
}