package com.fdzang.microservice.blog.web.feign.article;

import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.web.feign.article.impl.CommentHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/10 15:38
 */
@FeignClient(value = Constant.ServiceName.BLOG_ARTICLE,fallbackFactory = CommentHystrix.class)
public interface CommentClient {
    @RequestMapping(value = "/zuul/comment/getCommentByArticleId",method = RequestMethod.GET)
    ApiResult<List<CommentDTO>> getCommentByArticleId(@RequestParam("id") String id);
}
