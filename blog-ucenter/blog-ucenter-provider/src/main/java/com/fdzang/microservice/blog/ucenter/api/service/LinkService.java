package com.fdzang.microservice.blog.ucenter.api.service;

import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:22
 */
public interface LinkService {
    List<LinkDTO> getLinks();
    List<LinkDTO> getLinksByKeyWord(String keyword);
    Boolean addLink(LinkDTO link);
    LinkDTO getLinkById(String id);
    Boolean updateLink(LinkDTO link);
    Boolean deleteLink(String id);
}
