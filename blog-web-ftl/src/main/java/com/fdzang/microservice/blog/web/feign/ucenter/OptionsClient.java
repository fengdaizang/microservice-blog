package com.fdzang.microservice.blog.web.feign.ucenter;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import com.fdzang.microservice.blog.web.feign.ucenter.impl.OptionsHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:17
 */
@FeignClient(value = Constant.ServiceName.BLOG_UCENTER,fallbackFactory = OptionsHystrix.class)
public interface OptionsClient {
    @RequestMapping(value = "/zuul/options/getAllOptions",method = RequestMethod.GET)
    ApiResult<List<OptionsDTO>> getAllOptions();

    @RequestMapping(value = "/zuul/options/getOptionById",method = RequestMethod.GET)
    ApiResult<OptionsDTO> getOptionById(@RequestParam("id") String id);

    @RequestMapping(value = "/zuul/options/updateOption",method = RequestMethod.POST)
    ApiResult updateOption(@RequestBody OptionsDTO option);
}
