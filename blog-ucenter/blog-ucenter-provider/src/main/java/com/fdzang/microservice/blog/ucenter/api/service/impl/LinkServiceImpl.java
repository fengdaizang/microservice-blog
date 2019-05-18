package com.fdzang.microservice.blog.ucenter.api.service.impl;

import com.fdzang.microservice.blog.common.utils.TimeUtils;
import com.fdzang.microservice.blog.ucenter.api.service.LinkService;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.dao.domain.LinkDO;
import com.fdzang.microservice.blog.ucenter.dao.domain.LinkDOExample;
import com.fdzang.microservice.blog.ucenter.dao.mapper.LinkMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:22
 */
@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<LinkDTO> getLinks() {
        List<LinkDO> linkDOS=linkMapper.selectByExample(null);
        if(CollectionUtils.isNotEmpty(linkDOS)){
            List<LinkDTO> linkDTOS=new ArrayList<>();
            for (LinkDO linkDO:linkDOS) {
                LinkDTO linkDTO=new LinkDTO();
                BeanUtils.copyProperties(linkDO,linkDTO);
                linkDTOS.add(linkDTO);
            }
            return linkDTOS;
        }
        return null;
    }

    @Override
    public List<LinkDTO> getLinksByKeyWord(String keyword) {
        LinkDOExample example=new LinkDOExample();

        LinkDOExample.Criteria criteria2=new LinkDOExample().createCriteria();
        criteria2.andLinkTitleLike("%"+keyword+"%");

        example.createCriteria().andLinkDescriptionLike("%"+keyword+"%");
        example.or(criteria2);

        List<LinkDO> linkDOS=linkMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(linkDOS)){
            List<LinkDTO> linkDTOS=new ArrayList<>();
            for (LinkDO linkDO:linkDOS) {
                LinkDTO linkDTO=new LinkDTO();
                BeanUtils.copyProperties(linkDO,linkDTO);
                linkDTOS.add(linkDTO);
            }
            return linkDTOS;
        }
        return null;
    }

    @Override
    public Boolean addLink(LinkDTO link) {
        LinkDO linkDO=new LinkDO();
        BeanUtils.copyProperties(link,linkDO);

        linkDO.setId(TimeUtils.getTimestamp());
        linkDO.setLinkOrder(1);

        int count=linkMapper.insert(linkDO);

        return count>0;
    }

    @Override
    public LinkDTO getLinkById(String id) {
        LinkDO linkDO=linkMapper.selectByPrimaryKey(id);

        LinkDTO linkDTO=new LinkDTO();
        BeanUtils.copyProperties(linkDO,linkDTO);

        return linkDTO;
    }

    @Override
    public Boolean updateLink(LinkDTO link) {
        LinkDO linkDO=linkMapper.selectByPrimaryKey(link.getId());

        linkDO.setLinkAddress(link.getLinkAddress());
        linkDO.setLinkTitle(link.getLinkTitle());
        linkDO.setLinkDescription(link.getLinkDescription());

        int count=linkMapper.updateByPrimaryKey(linkDO);

        return count>0;
    }

    @Override
    public Boolean deleteLink(String id) {
        int count=linkMapper.deleteByPrimaryKey(id);

        return count>0;
    }
}
