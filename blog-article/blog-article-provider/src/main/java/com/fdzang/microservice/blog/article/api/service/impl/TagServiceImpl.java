package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.TagService;
import com.fdzang.microservice.blog.article.api.utils.ConvertUtils;
import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.dao.domain.TagArticleDO;
import com.fdzang.microservice.blog.article.dao.domain.TagArticleDOExample;
import com.fdzang.microservice.blog.article.dao.domain.TagDO;
import com.fdzang.microservice.blog.article.dao.domain.TagDOExample;
import com.fdzang.microservice.blog.article.dao.mapper.TagArticleMapper;
import com.fdzang.microservice.blog.article.dao.mapper.TagMapper;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:02
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Override
    public List<TagDTO> getMostUsedTags() {
        TagDOExample example=new TagDOExample();
        example.setOrderByClause(" tag_published_ref_count desc");
        example.setStartPos(0);
        example.setPageSize(Constant.Page.DEFAULTSIZE);
        example.createCriteria().andTagPublishedRefCountGreaterThan(0);

        List<TagDO> tagDOS=tagMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            return ConvertUtils.convertTagList(tagDOS);
        }
        return null;

    }

    @Override
    public List<TagDTO> getTags() {
        TagDOExample example=new TagDOExample();
        example.createCriteria().andTagPublishedRefCountGreaterThan(0);

        List<TagDO> tagDOS=tagMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            return ConvertUtils.convertTagList(tagDOS);
        }
        return null;
    }

    @Override
    public TagDTO getTagByTitle(String title) {
        TagDOExample example=new TagDOExample();
        example.createCriteria().andTagTitleEqualTo(title);

        List<TagDO> tagDOS=tagMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            TagDO tagDO=tagDOS.get(0);
            return ConvertUtils.convertTag(tagDO);
        }
        return null;
    }

    @Override
    public Boolean addArticleAndTag(String tags, String id, Boolean isPush) {
        try {
            String[] tag=tags.split(",");
            for (String t:tag) {
                if(StringUtils.isEmpty(t)){
                    continue;
                }

                TagDOExample example=new TagDOExample();
                example.createCriteria().andTagTitleEqualTo(t);
                List<TagDO> tagDOS=tagMapper.selectByExample(example);

                TagDO tagDO=new TagDO();
                String tid=TimeUtils.getTimestamp();
                if(CollectionUtils.isNotEmpty(tagDOS)){
                    tagDO=tagDOS.get(0);
                    tid = tagDO.getId();
                    //更新标签信息
                    tagDO.setTagReferenceCount(tagDO.getTagReferenceCount()+1);
                    if(isPush){
                        tagDO.setTagPublishedRefCount(tagDO.getTagPublishedRefCount()+1);
                    }
                    tagMapper.updateByPrimaryKey(tagDO);
                }else{
                    //先创建标签
                    tagDO.setId(tid);
                    tagDO.setTagReferenceCount(1);
                    if(isPush){
                        tagDO.setTagPublishedRefCount(1);
                    }else{
                        tagDO.setTagPublishedRefCount(0);
                    }
                    tagDO.setTagTitle(t);
                    tagMapper.insert(tagDO);
                }
                //关联文章与标签
                TagArticleDO tagArticleDO=new TagArticleDO();
                tagArticleDO.setId(TimeUtils.getTimestamp());
                tagArticleDO.setArticleId(id);
                tagArticleDO.setTagId(tid);
                tagArticleMapper.insert(tagArticleDO);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean deleteArticleAndTag(String id,Boolean isPush) {
        try {
            List<String> tids=new ArrayList<>();
            /**
             * 清理未关联的标签
             */
            TagArticleDOExample relExample=new TagArticleDOExample();
            relExample.createCriteria().andArticleIdEqualTo(id);
            List<TagArticleDO> tagArticleDOS=tagArticleMapper.selectByExample(relExample);
            if(CollectionUtils.isNotEmpty(tagArticleDOS)){
                for (TagArticleDO rel:tagArticleDOS) {
                    tids.add(rel.getTagId());
                    tagArticleMapper.deleteByPrimaryKey(rel.getId());
                }
            }

            /**
             * 减去去掉的关联数
             */
            TagDOExample example=new TagDOExample();
            example.createCriteria().andIdIn(tids);
            List<TagDO> tagDOS=tagMapper.selectByExample(example);
            if(CollectionUtils.isNotEmpty(tagDOS)) {
                for (TagDO tagDO:tagDOS) {
                    //更新标签信息
                    tagDO.setTagReferenceCount(tagDO.getTagReferenceCount()-1);
                    if(isPush){
                        tagDO.setTagPublishedRefCount(tagDO.getTagPublishedRefCount()-1);
                    }
                    tagMapper.updateByPrimaryKey(tagDO);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Integer deleteTagNoUse() {
        TagDOExample example=new TagDOExample();
        example.createCriteria().andTagReferenceCountLessThan(1);

        List<TagDO> tagDOS=tagMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            for (TagDO tag:tagDOS) {
                tagMapper.deleteByPrimaryKey(tag.getId());
            }

            return tagDOS.size();
        }

        return 0;
    }
}
