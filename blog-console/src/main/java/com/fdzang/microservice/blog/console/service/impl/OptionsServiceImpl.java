package com.fdzang.microservice.blog.console.service.impl;

import com.fdzang.microservice.blog.console.service.OptionsService;
import com.fdzang.microservice.blog.ucenter.feign.client.OptionsClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tanghu
 * @Date: 2019/1/14 14:14
 */
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsClient optionsClient;

}
