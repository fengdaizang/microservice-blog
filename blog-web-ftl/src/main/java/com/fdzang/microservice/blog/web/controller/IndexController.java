package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.web.utils.CaptchaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private HttpSession session;

    @RequestMapping({"/index","","/"})
    public String index(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                        HashMap<String,Object> map){
        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isEmpty(keyword)){
            keyword="";
        }else {
            session.removeAttribute(Constant.Session.KEYWORD);
        }
        PageDTO<ArticleDTO> pageDTO=(PageDTO<ArticleDTO>) CoventUtils.getApiResultData(
                articleClient.getArticles(keyword, pageNo, pageSize));

        map.put(Constant.Page.PAGE,pageDTO);
        String path="/index.html?pageNo=";
        if(pageSize!=5){
            path="/index.html?pageSize="+pageSize+"&pageNo=";
        }
        map.put(Constant.Session.PATH,path);

        return Constant.IndexHtml.INDEX;
    }

    @RequestMapping("/admin/index")
    public String adminIndex(){
        UserDTO user = (UserDTO)session.getAttribute(Constant.User.USER);
        if(user==null){
            return Constant.AdminHtml.LOGIN;
        }else{
            return Constant.AdminHtml.INDEX;
        }
    }

    @ResponseBody
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword){

        session.setAttribute(Constant.Session.KEYWORD,keyword);
        return "OK";
    }

    @RequestMapping("/register")
    public String showRegister(){
        return Constant.AdminHtml.REGISTER;
    }

    @RequestMapping("/login")
    public String login(){
        return Constant.AdminHtml.LOGIN;
    }

    @RequestMapping("/logout")
    public String logout(){
        session.setAttribute(Constant.User.ISLOGGEDIN,false);
        session.removeAttribute(Constant.User.USER);

        return "redirect:index.html";
    }

    /**
     * 获取验证码
     * @param response
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletResponse response) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String checkCodeValue = CaptchaUtils.drawImg(output);
        //将生成的验证码存入session
        session.setAttribute(Constant.Comment.CAPTCHA, checkCodeValue);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取验证码
     * @param response
     */
    @RequestMapping("/replyCaptcha")
    public void replyCaptcha(HttpServletResponse response) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String checkCodeValue = CaptchaUtils.drawImg(output);
        //将生成的验证码存入session
        session.setAttribute(Constant.Comment.REPLY_CAPTCHA, checkCodeValue);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
