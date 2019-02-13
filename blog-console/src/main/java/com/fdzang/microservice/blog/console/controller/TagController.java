package com.fdzang.microservice.blog.console.controller;

import com.fdzang.microservice.blog.article.feign.client.TagClient;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/16 9:33
 */
@RestController
@RequestMapping("/zuul/tag")
@Validated
@Slf4j
public class TagController extends BaseController {

}
