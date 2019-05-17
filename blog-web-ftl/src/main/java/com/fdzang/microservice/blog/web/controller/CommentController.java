package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/4/16 21:41
 */
@Controller
public class CommentController extends BaseController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CommentClient commentClient;

    @GetMapping("/dynamic")
    public String dynamic(){
        List<CommentDTO> recentcomments=(List<CommentDTO>) CoventUtils.getApiResultData(commentClient.getRecentComments());

        session.setAttribute(Constant.Session.RECENTCOMMENTS,recentcomments);

        return Constant.Html.DYNAMIC;
    }
}
