package com.fdzang.microservice.blog.ucenter.api.service.impl;

import com.fdzang.microservice.blog.ucenter.api.service.LinkService;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.dao.domain.LinkDO;
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
}
