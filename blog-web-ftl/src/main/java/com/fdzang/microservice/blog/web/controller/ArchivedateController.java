package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.ArchivedateClient;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/archives")
    public String archives(HashMap<String,Object> map){
        List<ArchivedateDTO> archives=(List<ArchivedateDTO>) CoventUtils.getApiResultData(archivedateClient.getArchives());

        map.put(Constant.ArchiveDate.ARCHIVEDATES,archives);

        System.out.println(archives);
        return Constant.IndexHtml.ARCHIVES;
    }

    @GetMapping("/archives/{year}/{month}")
    public String getArticleByArchive(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                       @PathVariable("year") Integer year,
                       @PathVariable("month") Integer month,
                       HashMap<String,Object> map){
        ArchivedateDTO archive=(ArchivedateDTO) CoventUtils.getApiResultData(archivedateClient.getArchiveByTime(year, month));
        map.put(Constant.ArchiveDate.ARCHIVEDATE,archive);

        PageDTO<ArticleDTO> pageDTO=(PageDTO<ArticleDTO>) CoventUtils.getApiResultData(
                articleClient.getArticlesByArchiveId(archive.getId(),pageNo,pageSize));

        map.put(Constant.Page.PAGE,pageDTO);
        String path="/archives/"+year+"/"+month+".html?pageNo=";
        if(pageSize!=5){
            path="/archives/"+year+"/"+month+".html?pageSize="+pageSize+"&pageNo=";
        }
        map.put(Constant.Session.PATH,path);

        return Constant.IndexHtml.ARCHIVE_ARTICLES;
    }

}
