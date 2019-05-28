package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.OptionsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private OptionsClient optionsClient;

    @GetMapping("/dynamic")
    public String dynamic(HashMap<String,Object> map){
        List<CommentDTO> recentcomments=(List<CommentDTO>) CoventUtils.getApiResultData(commentClient.getRecentComments());

        map.put(Constant.Comment.RECENTCOMMENTS,recentcomments);

        return Constant.IndexHtml.DYNAMIC;
    }

    @GetMapping("/comment/mgr")
    public String commentMgr(HashMap<String,Object> map){
        UserDTO userDTO=getCurrentUser();
        if(Constant.UserRole.ADMIN.equals(userDTO.getUserRole())){
            List<CommentDTO> comments=(List<CommentDTO>) CoventUtils.getApiResultData(commentClient.getComments());

            map.put(Constant.Comment.COMMENTS,comments);
        }else{
            List<CommentDTO> comments=(List<CommentDTO>) CoventUtils.getApiResultData(
                    commentClient.getCommentsByUserEmail(userDTO.getUserEmail()));

            map.put(Constant.Comment.COMMENTS,comments);
        }

        return Constant.AdminHtml.COMMENT;
    }

    @ResponseBody
    @GetMapping("/comment/delete")
    public Boolean commentMgr(String id){
        Boolean bool = (Boolean)CoventUtils.getApiResultData(commentClient.deleteComment(id));

        optionsClient.incrementById(Constant.Static.BLOG_COMMENT_COUNT,-1);

        return bool;
    }

    @ResponseBody
    @GetMapping("/comment/submitNoLogin")
    public String commentSubmitNoLogin(@RequestParam("articleId")String articleId,
                                        @RequestParam("commentName")String commentName,
                                        @RequestParam("commentEmail")String commentEmail,
                                        @RequestParam("commentValidate")String commentValidate,
                                        @RequestParam("commentURL")String commentURL,
                                        @RequestParam("comment")String comment){
        String captcha=(String) session.getAttribute(Constant.Comment.CAPTCHA);
        if(!captcha.equalsIgnoreCase(commentValidate)){
            return "验证码不正确";
        }
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentArticleId(articleId);
        commentDTO.setCommentUrl(commentURL);
        commentDTO.setCommentName(commentName);
        commentDTO.setCommentContent(comment);
        commentDTO.setCommentEmail(commentEmail);

        Boolean bool=(Boolean) CoventUtils.getApiResultData(commentClient.addComment(commentDTO));
        optionsClient.incrementById(Constant.Static.BLOG_COMMENT_COUNT,1);
        if(bool){
            return "ok";
        }else{
            return "回复失败！";
        }
    }

    @ResponseBody
    @GetMapping("/comment/submit")
    public String commentSubmit(@RequestParam("articleId")String articleId,
                                       @RequestParam("comment")String comment){
        UserDTO userDTO=getCurrentUser();

        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentArticleId(articleId);
        commentDTO.setCommentUrl(userDTO.getUserUrl());
        commentDTO.setCommentName(userDTO.getUserName());
        commentDTO.setCommentContent(comment);
        commentDTO.setCommentEmail(userDTO.getUserEmail());
        commentDTO.setCommentThumbnailUrl(userDTO.getUserAvatar());

        Boolean bool=(Boolean) CoventUtils.getApiResultData(commentClient.addComment(commentDTO));
        optionsClient.incrementById(Constant.Static.BLOG_COMMENT_COUNT,1);

        if(bool){
            return "ok";
        }else{
            return "回复失败！";
        }
    }

    @ResponseBody
    @GetMapping("/comment/reply")
    public String commentReply(@RequestParam("commentId")String commentId,
                                @RequestParam("comment")String comment){
        UserDTO userDTO=getCurrentUser();

        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentOriginalCommentId(commentId);
        commentDTO.setCommentUrl(userDTO.getUserUrl());
        commentDTO.setCommentName(userDTO.getUserName());
        commentDTO.setCommentContent(comment);
        commentDTO.setCommentEmail(userDTO.getUserEmail());
        commentDTO.setCommentThumbnailUrl(userDTO.getUserAvatar());

        Boolean bool=(Boolean) CoventUtils.getApiResultData(commentClient.replyComment(commentDTO));
        optionsClient.incrementById(Constant.Static.BLOG_COMMENT_COUNT,1);

        if(bool){
            return "ok";
        }else{
            return "回复失败！";
        }
    }

    @ResponseBody
    @GetMapping("/comment/replyNoLogin")
    public String commentReplyNoLogin(@RequestParam("commentId")String commentId,
                                       @RequestParam("commentName")String commentName,
                                       @RequestParam("commentEmail")String commentEmail,
                                       @RequestParam("commentValidate")String commentValidate,
                                       @RequestParam("commentURL")String commentURL,
                                       @RequestParam("comment")String comment){
        String captcha=(String) session.getAttribute(Constant.Comment.REPLY_CAPTCHA);
        if(!captcha.equalsIgnoreCase(commentValidate)){
            return "验证码不正确";
        }
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentOriginalCommentId(commentId);
        commentDTO.setCommentUrl(commentURL);
        commentDTO.setCommentName(commentName);
        commentDTO.setCommentContent(comment);
        commentDTO.setCommentEmail(commentEmail);

        Boolean bool=(Boolean) CoventUtils.getApiResultData(commentClient.replyComment(commentDTO));
        optionsClient.incrementById(Constant.Static.BLOG_COMMENT_COUNT,1);

        if(bool){
            return "ok";
        }else{
            return "回复失败！";
        }
    }
}
