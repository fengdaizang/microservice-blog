package com.fdzang.microservice.blog.console.service.impl;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.feign.client.CommentClient;
import com.fdzang.microservice.blog.console.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/14 14:14
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentClient commentClient;

}
