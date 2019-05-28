package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.LinkClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 23:29
 */
@Controller
public class LinkController extends BaseController {

    @Autowired
    private LinkClient linkClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/links")
    public String links(HashMap<String,Object> map){
        List<LinkDTO> linkDTOS=(List<LinkDTO>) CoventUtils.getApiResultData(linkClient.getLinks());

        map.put(Constant.Session.LINKS,linkDTOS);

        return Constant.IndexHtml.LINKS;
    }

    @GetMapping("/link/mgr")
    public String linkMgr(HashMap<String,Object> map){
        List<LinkDTO> linkDTOS=null;

        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isNotEmpty(keyword)){
            linkDTOS=(List<LinkDTO>) CoventUtils.getApiResultData(linkClient.getLinksByKeyword(keyword));

            session.removeAttribute(Constant.Session.KEYWORD);
        }else{
            linkDTOS=(List<LinkDTO>) CoventUtils.getApiResultData(linkClient.getLinks());
        }

        map.put(Constant.Session.LINKS,linkDTOS);

        return Constant.AdminHtml.LINK;
    }

    @ResponseBody
    @GetMapping("/link/search")
    public Boolean linkSearch(@RequestParam("keyword")String keyword){
        List<LinkDTO> linkDTOS=(List<LinkDTO>) CoventUtils.getApiResultData(linkClient.getLinksByKeyword(keyword));

        if(linkDTOS !=null){
            session.setAttribute(Constant.Session.KEYWORD,keyword);

            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @PostMapping(value = "link/add")
    public Boolean linkAdd(@ModelAttribute LinkDTO link){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(linkClient.addLink(link));

        return bool;
    }

    @ResponseBody
    @GetMapping("link/getLinkById")
    public LinkDTO linkAdd(@RequestParam("id") String id){
        LinkDTO linkDTO=(LinkDTO) CoventUtils.getApiResultData(linkClient.getLinksById(id));

        return linkDTO;
    }

    @ResponseBody
    @PostMapping("link/update")
    public Boolean linkUpdate(@ModelAttribute LinkDTO link){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(linkClient.updateLink(link));

        return bool;
    }

    @ResponseBody
    @GetMapping("link/delete")
    public Boolean linkDelete(@RequestParam("id") String id){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(linkClient.deleteLink(id));

        return bool;
    }
}
