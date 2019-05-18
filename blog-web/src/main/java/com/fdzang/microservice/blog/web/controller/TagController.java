package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.common.dto.TagDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        session.setAttribute(Constant.Session.TAGS,tags);

        return Constant.IndexHtml.TAGS;
    }

    @GetMapping("/tags/{title}")
    public String tag(Integer pageNO,Integer pageSize,
                      @PathVariable("title") String title){
        TagDTO tag=(TagDTO) CoventUtils.getApiResultData(tagClient.getTagByTitle(title));
        session.setAttribute(Constant.Session.TAG,tag);

        if(pageNO==null){
            pageNO=0;
        }
        if(pageSize==null){
            pageSize=10;
        }
        ApiResult<PageDTO<ArticleDTO>> result=articleClient.getArticlesByArchiveId(tag.getId(),pageNO,pageSize);
        PageDTO<ArticleDTO> data=result.getData();
        int paginationPageCount=data.getTotalPage();
        List<ArticleDTO> articles=data.getResult();

        session.setAttribute(Constant.Session.ARTICLES,articles);
        session.setAttribute(Constant.Session.PAGINATIONPAGECOUNT,paginationPageCount);

        return Constant.IndexHtml.TAG_ARTICLES;
    }
}
