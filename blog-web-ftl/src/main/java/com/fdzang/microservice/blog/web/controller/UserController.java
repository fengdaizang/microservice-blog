package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.UserClient;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author tanghu
 * @Date: 2019/1/9 11:16
 */
@Controller
@RequestMapping("/user")
@Validated
public class UserController extends BaseController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public String userLogin(@RequestParam("username")String username,
                            @RequestParam("password")String password,
                            HashMap<String,Object> map){

        ApiResult<UserDTO> result=userClient.userLogin(username, password);
        if(result.getCode()!= ErrorCode.SUCCESS){
            map.put(Constant.Static.MSG,result.getMsg());

            return Constant.IndexHtml.LOGIN;
        }else{
            request.getSession().setAttribute(Constant.Session.USER,result.getData());

            UserDTO userDTO=(UserDTO) CoventUtils.getApiResultData(result);

            if(Constant.UserRole.VISITOR.equals(userDTO.getUserRole())){
                return "redirect:index.html";
            }else{
                return "admin/index";
            }
        }
    }

    @ResponseBody
    @RequestMapping("/isExistEmail")
    public Boolean isExistEmail(@RequestParam("email")String email){

        ApiResult<UserDTO> result= userClient.getUserByEmail(email);

        if(result!=null && result.getCode()==ErrorCode.SUCCESS
                && result.getData()!=null){
            return true;
        }

        return false;
    }

    @RequestMapping("/register")
    public String register(@RequestParam("userEmail")String userEmail,
                           @RequestParam("userName")String userName,
                           @RequestParam("userUrl")String userUrl,
                           @RequestParam("password")String password){
        UserDTO userDTO=new UserDTO();
        userDTO.setUserEmail(userEmail);
        userDTO.setUserName(userName);
        userDTO.setUserUrl(userUrl);
        userDTO.setPassword(password);

        ApiResult<Boolean> result=userClient.addUser(userDTO);
        Boolean bool=(Boolean)CoventUtils.getApiResultData(result);
        if(result.getCode()!= ErrorCode.SUCCESS){
            request.getSession().setAttribute(Constant.Static.MSG,result.getMsg());

            return Constant.IndexHtml.REGISTER;
        }else{
            if(bool){
                return Constant.IndexHtml.LOGIN;
            }else{
                return Constant.IndexHtml.REGISTER;
            }
        }
    }
}
