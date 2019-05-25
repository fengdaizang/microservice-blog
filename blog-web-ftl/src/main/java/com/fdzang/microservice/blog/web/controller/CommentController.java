package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

        return Constant.IndexHtml.DYNAMIC;
    }

    @GetMapping("/comment/mgr")
    public String commentMgr(HashMap<String,Object> map){
        UserDTO userDTO=getCurrentUser();
        if(Constant.UserRole.ADMIN.equals(userDTO.getUserRole())){
            List<CommentDTO> comments=(List<CommentDTO>) CoventUtils.getApiResultData(commentClient.getComments());

            map.put(Constant.Session.COMMENTS,comments);
        }else{
            List<CommentDTO> comments=(List<CommentDTO>) CoventUtils.getApiResultData(
                    commentClient.getCommentsByUserEmail(userDTO.getUserEmail()));

            map.put(Constant.Session.COMMENTS,comments);
        }

        return Constant.AdminHtml.COMMENT;
    }

    @ResponseBody
    @GetMapping("/comment/delete")
    public Boolean commentMgr(String id){
        Boolean bool = (Boolean)CoventUtils.getApiResultData(commentClient.deleteComment(id));

        return bool;
    }
}
