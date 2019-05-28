package com.fdzang.microservice.blog.ucenter.feign.client;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.impl.OptionsHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:11
 */
@FeignClient(value = Constant.ServiceName.BLOG_UCENTER,fallbackFactory = OptionsHystrix.class)
public interface OptionsClient {
    @GetMapping(value = "/zuul/options/getAllOptions")
    ApiResult<List<OptionsDTO>> getAllOptions();

    @GetMapping(value = "/zuul/options/getOptionById")
    ApiResult<OptionsDTO> getOptionById(@RequestParam("id") String id);

    @GetMapping(value = "/zuul/options/incrementById")
    ApiResult<Boolean> incrementById(@RequestParam("id") String id,
                                     @RequestParam("num") Integer num);

    @GetMapping(value = "/zuul/options/updateOptionByKV")
    ApiResult<Boolean> updateOptionByKV(@RequestParam("id") String id,
                                        @RequestParam("value") String value);
}
