package com.fdzang.microservice.blog.console.controller;

import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.console.service.OptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghu
 * @Date: 2019/1/16 9:19
 */
@RestController
@RequestMapping("/zuul/options")
@Validated
@Slf4j
public class OptionsController extends BaseController {

}
