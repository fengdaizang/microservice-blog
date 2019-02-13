package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.TagService;
import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.dao.domain.TagDO;
import com.fdzang.microservice.blog.article.dao.domain.TagDOExample;
import com.fdzang.microservice.blog.article.dao.mapper.TagMapper;
import com.fdzang.microservice.blog.common.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
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

    @Override
    public List<TagDTO> getMostUsedTags() {
        TagDOExample example=new TagDOExample();
        example.setOrderByClause("tag_published_ref_count desc");
        example.setStartPos(0);
        example.setPageSize(Constant.Page.PAGESIZE);

        List<TagDO> tagDOS=tagMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            List<TagDTO> tagDTOS=new ArrayList<>();
            for (TagDO tag:tagDOS) {
                TagDTO tagDTO=new TagDTO();
                BeanUtils.copyProperties(tag,tagDTO);
                tagDTOS.add(tagDTO);
            }
            return tagDTOS;
        }
        return null;

    }

    @Override
    public List<TagDTO> getTags() {
        List<TagDO> tagDOS=tagMapper.selectByExample(null);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            List<TagDTO> tagDTOS=new ArrayList<>();
            for (TagDO tag:tagDOS) {
                TagDTO tagDTO=new TagDTO();
                BeanUtils.copyProperties(tag,tagDTO);
                tagDTOS.add(tagDTO);
            }
            return tagDTOS;
        }
        return null;
    }

    @Override
    public TagDTO getTagByTitle(String title) {
        TagDOExample example=new TagDOExample();
        example.createCriteria().andTagTitleEqualTo(title);

        List<TagDO> tagDOS=tagMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tagDOS)){
            TagDTO tagDTO=new TagDTO();
            TagDO tagDO=tagDOS.get(0);
            BeanUtils.copyProperties(tagDO,tagDTO);
            return tagDTO;
        }
        return null;
    }
}
