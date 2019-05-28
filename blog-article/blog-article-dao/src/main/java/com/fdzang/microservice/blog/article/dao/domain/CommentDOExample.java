package com.fdzang.microservice.blog.article.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDOExample {
    protected Integer startPos;
    protected Integer pageSize;

    public Integer getStartPos() {
        return startPos;
    }

    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table comment
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table comment
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table comment
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public CommentDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table comment
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCommentDateIsNull() {
            addCriterion("comment_date is null");
            return (Criteria) this;
        }

        public Criteria andCommentDateIsNotNull() {
            addCriterion("comment_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommentDateEqualTo(Date value) {
            addCriterion("comment_date =", value, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateNotEqualTo(Date value) {
            addCriterion("comment_date <>", value, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateGreaterThan(Date value) {
            addCriterion("comment_date >", value, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateGreaterThanOrEqualTo(Date value) {
            addCriterion("comment_date >=", value, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateLessThan(Date value) {
            addCriterion("comment_date <", value, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateLessThanOrEqualTo(Date value) {
            addCriterion("comment_date <=", value, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateIn(List<Date> values) {
            addCriterion("comment_date in", values, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateNotIn(List<Date> values) {
            addCriterion("comment_date not in", values, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateBetween(Date value1, Date value2) {
            addCriterion("comment_date between", value1, value2, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentDateNotBetween(Date value1, Date value2) {
            addCriterion("comment_date not between", value1, value2, "commentDate");
            return (Criteria) this;
        }

        public Criteria andCommentEmailIsNull() {
            addCriterion("comment_email is null");
            return (Criteria) this;
        }

        public Criteria andCommentEmailIsNotNull() {
            addCriterion("comment_email is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEmailEqualTo(String value) {
            addCriterion("comment_email =", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailNotEqualTo(String value) {
            addCriterion("comment_email <>", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailGreaterThan(String value) {
            addCriterion("comment_email >", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailGreaterThanOrEqualTo(String value) {
            addCriterion("comment_email >=", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailLessThan(String value) {
            addCriterion("comment_email <", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailLessThanOrEqualTo(String value) {
            addCriterion("comment_email <=", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailLike(String value) {
            addCriterion("comment_email like", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailNotLike(String value) {
            addCriterion("comment_email not like", value, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailIn(List<String> values) {
            addCriterion("comment_email in", values, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailNotIn(List<String> values) {
            addCriterion("comment_email not in", values, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailBetween(String value1, String value2) {
            addCriterion("comment_email between", value1, value2, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentEmailNotBetween(String value1, String value2) {
            addCriterion("comment_email not between", value1, value2, "commentEmail");
            return (Criteria) this;
        }

        public Criteria andCommentNameIsNull() {
            addCriterion("comment_name is null");
            return (Criteria) this;
        }

        public Criteria andCommentNameIsNotNull() {
            addCriterion("comment_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNameEqualTo(String value) {
            addCriterion("comment_name =", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameNotEqualTo(String value) {
            addCriterion("comment_name <>", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameGreaterThan(String value) {
            addCriterion("comment_name >", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameGreaterThanOrEqualTo(String value) {
            addCriterion("comment_name >=", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameLessThan(String value) {
            addCriterion("comment_name <", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameLessThanOrEqualTo(String value) {
            addCriterion("comment_name <=", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameLike(String value) {
            addCriterion("comment_name like", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameNotLike(String value) {
            addCriterion("comment_name not like", value, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameIn(List<String> values) {
            addCriterion("comment_name in", values, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameNotIn(List<String> values) {
            addCriterion("comment_name not in", values, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameBetween(String value1, String value2) {
            addCriterion("comment_name between", value1, value2, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentNameNotBetween(String value1, String value2) {
            addCriterion("comment_name not between", value1, value2, "commentName");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdIsNull() {
            addCriterion("comment_article_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdIsNotNull() {
            addCriterion("comment_article_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdEqualTo(String value) {
            addCriterion("comment_article_id =", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdNotEqualTo(String value) {
            addCriterion("comment_article_id <>", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdGreaterThan(String value) {
            addCriterion("comment_article_id >", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdGreaterThanOrEqualTo(String value) {
            addCriterion("comment_article_id >=", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdLessThan(String value) {
            addCriterion("comment_article_id <", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdLessThanOrEqualTo(String value) {
            addCriterion("comment_article_id <=", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdLike(String value) {
            addCriterion("comment_article_id like", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdNotLike(String value) {
            addCriterion("comment_article_id not like", value, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdIn(List<String> values) {
            addCriterion("comment_article_id in", values, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdNotIn(List<String> values) {
            addCriterion("comment_article_id not in", values, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdBetween(String value1, String value2) {
            addCriterion("comment_article_id between", value1, value2, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentArticleIdNotBetween(String value1, String value2) {
            addCriterion("comment_article_id not between", value1, value2, "commentArticleId");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlIsNull() {
            addCriterion("comment_sharp_url is null");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlIsNotNull() {
            addCriterion("comment_sharp_url is not null");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlEqualTo(String value) {
            addCriterion("comment_sharp_url =", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlNotEqualTo(String value) {
            addCriterion("comment_sharp_url <>", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlGreaterThan(String value) {
            addCriterion("comment_sharp_url >", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlGreaterThanOrEqualTo(String value) {
            addCriterion("comment_sharp_url >=", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlLessThan(String value) {
            addCriterion("comment_sharp_url <", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlLessThanOrEqualTo(String value) {
            addCriterion("comment_sharp_url <=", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlLike(String value) {
            addCriterion("comment_sharp_url like", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlNotLike(String value) {
            addCriterion("comment_sharp_url not like", value, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlIn(List<String> values) {
            addCriterion("comment_sharp_url in", values, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlNotIn(List<String> values) {
            addCriterion("comment_sharp_url not in", values, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlBetween(String value1, String value2) {
            addCriterion("comment_sharp_url between", value1, value2, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentSharpUrlNotBetween(String value1, String value2) {
            addCriterion("comment_sharp_url not between", value1, value2, "commentSharpUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlIsNull() {
            addCriterion("comment_url is null");
            return (Criteria) this;
        }

        public Criteria andCommentUrlIsNotNull() {
            addCriterion("comment_url is not null");
            return (Criteria) this;
        }

        public Criteria andCommentUrlEqualTo(String value) {
            addCriterion("comment_url =", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlNotEqualTo(String value) {
            addCriterion("comment_url <>", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlGreaterThan(String value) {
            addCriterion("comment_url >", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlGreaterThanOrEqualTo(String value) {
            addCriterion("comment_url >=", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlLessThan(String value) {
            addCriterion("comment_url <", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlLessThanOrEqualTo(String value) {
            addCriterion("comment_url <=", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlLike(String value) {
            addCriterion("comment_url like", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlNotLike(String value) {
            addCriterion("comment_url not like", value, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlIn(List<String> values) {
            addCriterion("comment_url in", values, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlNotIn(List<String> values) {
            addCriterion("comment_url not in", values, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlBetween(String value1, String value2) {
            addCriterion("comment_url between", value1, value2, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentUrlNotBetween(String value1, String value2) {
            addCriterion("comment_url not between", value1, value2, "commentUrl");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdIsNull() {
            addCriterion("comment_original_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdIsNotNull() {
            addCriterion("comment_original_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdEqualTo(String value) {
            addCriterion("comment_original_comment_id =", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdNotEqualTo(String value) {
            addCriterion("comment_original_comment_id <>", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdGreaterThan(String value) {
            addCriterion("comment_original_comment_id >", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdGreaterThanOrEqualTo(String value) {
            addCriterion("comment_original_comment_id >=", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdLessThan(String value) {
            addCriterion("comment_original_comment_id <", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdLessThanOrEqualTo(String value) {
            addCriterion("comment_original_comment_id <=", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdLike(String value) {
            addCriterion("comment_original_comment_id like", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdNotLike(String value) {
            addCriterion("comment_original_comment_id not like", value, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdIn(List<String> values) {
            addCriterion("comment_original_comment_id in", values, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdNotIn(List<String> values) {
            addCriterion("comment_original_comment_id not in", values, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdBetween(String value1, String value2) {
            addCriterion("comment_original_comment_id between", value1, value2, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentIdNotBetween(String value1, String value2) {
            addCriterion("comment_original_comment_id not between", value1, value2, "commentOriginalCommentId");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameIsNull() {
            addCriterion("comment_original_comment_name is null");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameIsNotNull() {
            addCriterion("comment_original_comment_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameEqualTo(String value) {
            addCriterion("comment_original_comment_name =", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameNotEqualTo(String value) {
            addCriterion("comment_original_comment_name <>", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameGreaterThan(String value) {
            addCriterion("comment_original_comment_name >", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameGreaterThanOrEqualTo(String value) {
            addCriterion("comment_original_comment_name >=", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameLessThan(String value) {
            addCriterion("comment_original_comment_name <", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameLessThanOrEqualTo(String value) {
            addCriterion("comment_original_comment_name <=", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameLike(String value) {
            addCriterion("comment_original_comment_name like", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameNotLike(String value) {
            addCriterion("comment_original_comment_name not like", value, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameIn(List<String> values) {
            addCriterion("comment_original_comment_name in", values, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameNotIn(List<String> values) {
            addCriterion("comment_original_comment_name not in", values, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameBetween(String value1, String value2) {
            addCriterion("comment_original_comment_name between", value1, value2, "commentOriginalCommentName");
            return (Criteria) this;
        }

        public Criteria andCommentOriginalCommentNameNotBetween(String value1, String value2) {
            addCriterion("comment_original_comment_name not between", value1, value2, "commentOriginalCommentName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table comment
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table comment
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}