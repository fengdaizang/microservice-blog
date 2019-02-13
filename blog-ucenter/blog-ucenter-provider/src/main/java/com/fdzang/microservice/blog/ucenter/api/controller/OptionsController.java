package com.fdzang.microservice.blog.ucenter.api.controller;

import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.framework.BaseController;
import com.fdzang.microservice.blog.ucenter.api.service.OptionsService;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:13
 */
@RestController
@RequestMapping("/zuul/options")
public class OptionsController extends BaseController {

    @Autowired
    private OptionsService optionsService;

    @GetMapping(value = "/getAllOptions")
    public ApiResult getAllOptions(){
        return ok(optionsService.getAllOptions());
    }

    @GetMapping(value = "/getOptionById")
    public ApiResult getOptionById(@RequestParam("id") String id){
        return ok(optionsService.getOptionById(id));
    }

    @PostMapping(value = "/updateOption")
    public ApiResult updateOption(@RequestBody OptionsDTO option){
        if(optionsService.updateOption(option)){
            return ok();
        }else{
            return fail("更新失败！");
        }
    }
}
