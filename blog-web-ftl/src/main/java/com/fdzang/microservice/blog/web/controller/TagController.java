package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/14 10:07
 */
@Controller
@Validated
public class TagController {

    @Autowired
    private TagClient tagClient;

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/tags")
    public String tags(){
        List<TagDTO> tags=(List<TagDTO>) CoventUtils.getApiResultData(tagClient.getTags());

        session.setAttribute(Constant.Tag.TAGS,tags);

        return Constant.IndexHtml.TAGS;
    }

    @GetMapping("/tags/{title}")
    public String tag(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                      @PathVariable("title") String title){
        TagDTO tag=(TagDTO) CoventUtils.getApiResultData(tagClient.getTagByTitle(title));
        session.setAttribute(Constant.Tag.TAG,tag);

        PageDTO<ArticleDTO> pageDTO=(PageDTO<ArticleDTO>) CoventUtils.getApiResultData(
                articleClient.getArticlesByTagId(tag.getId(),pageNo,pageSize));

        session.setAttribute(Constant.Page.PAGE,pageDTO);
        session.setAttribute(Constant.Session.PATH,"/tags/"+title+".html?pageNo=");

        return Constant.IndexHtml.TAG_ARTICLES;
    }

    @GetMapping("/tag/mgr")
    public String tagMgr(){

        return Constant.AdminHtml.OTHERS;
    }

    @ResponseBody
    @GetMapping("/tag/delete")
    public Integer tagDelete(){
        Integer count=(Integer) CoventUtils.getApiResultData(tagClient.deleteNoUseTag());

        return count;
    }
}
