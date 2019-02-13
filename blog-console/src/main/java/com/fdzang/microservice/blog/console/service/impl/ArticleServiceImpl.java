package com.fdzang.microservice.blog.console.service.impl;

import com.fdzang.microservice.blog.article.common.dto.ArticleDTO;
import com.fdzang.microservice.blog.article.feign.client.ArticleClient;
import com.fdzang.microservice.blog.common.entity.PageDTO;
import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.console.service.ArticleService;
import com.fdzang.microservice.blog.console.utils.MarkDown2HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/10 15:01
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleClient articleClient;


}
