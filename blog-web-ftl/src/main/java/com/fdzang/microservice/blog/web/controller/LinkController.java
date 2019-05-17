package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.LinkDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.LinkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
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
    public String links(){
        List<LinkDTO> linkDTOS=(List<LinkDTO>) CoventUtils.getApiResultData(linkClient.getLinks());

        session.setAttribute(Constant.Session.LINKS,linkDTOS);

        return Constant.Html.LINKS;
    }
}
