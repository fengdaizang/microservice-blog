package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.ucenter.feign.client.OptionsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author tanghu
 * @Date: 2019/5/26 20:08
 */
@Controller
@Validated
public class OptionsController {

    @Autowired
    private OptionsClient optionsClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/options/mgr")
    public String optionsEdit(){

        return Constant.AdminHtml.OPTIONS;
    }

    @PostMapping("/options/update")
    public String archives(@RequestParam(required = false) Map<String,String> map){
        for (Map.Entry<String,String> option:map.entrySet()) {
            String id=option.getKey();
            String value=option.getValue();
            optionsClient.updateOptionByKV(id,value);

            session.setAttribute(option.getKey(),option.getValue());
        }

        return Constant.AdminHtml.OPTIONS;
    }
}
