package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.ArchivedateClient;
import com.fdzang.microservice.blog.web.feign.article.ArticleClient;
import com.fdzang.microservice.blog.web.utils.CoventUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author tanghu
 * @Date: 2019/1/21 14:24
 */
@Controller
public class ArchivedateController extends BaseController {

    @Autowired
    private ArchivedateClient archivedateClient;

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/archives")
    public String archives(Map<String,Object> map){
        List<ArchivedateDTO> archives=(List<ArchivedateDTO>) CoventUtils.getApiResultData(archivedateClient.getArchives());

        session.setAttribute(Constant.Session.ARCHIVEDATES,archives);

        System.out.println(archives);
        return Constant.Html.ARCHIVES;
    }

    @GetMapping("/archives/{year}/{month}")
    public String tags(Integer pageNO,Integer pageSize,
                       @PathVariable("year") Integer year,
                       @PathVariable("month") Integer month){
        ArchivedateDTO archive=(ArchivedateDTO) CoventUtils.getApiResultData(archivedateClient.getArchiveByTime(year, month));
        session.setAttribute(Constant.Session.ARCHIVEDATE,archive);

        if(pageNO==null){
            pageNO=0;
        }
        if(pageSize==null){
            pageSize=10;
        }
        ApiResult<PageDTO<ArticleDTO>> result=articleClient.getArticlesByArchiveId(archive.getId(),pageNO,pageSize);
        PageDTO<ArticleDTO> data=result.getData();
        int paginationPageCount=data.getTotalPage();
        List<ArticleDTO> articles=data.getResult();

        session.setAttribute(Constant.Session.ARTICLES,articles);
        session.setAttribute(Constant.Session.PAGINATIONPAGECOUNT,paginationPageCount);

        return Constant.Html.ARCHIVE_ARTICLES;
    }

}
