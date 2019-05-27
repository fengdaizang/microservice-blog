package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.fdzang.microservice.blog.common.utils.Constant;
import com.fdzang.microservice.blog.common.utils.CoventUtils;
import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import com.fdzang.microservice.blog.ucenter.feign.client.UserClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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
    private HttpSession session;

    @RequestMapping("/login")
    public String userLogin(@RequestParam("username")String username,
                            @RequestParam("password")String password,
                            HashMap<String,Object> map){

        ApiResult<UserDTO> result=userClient.userLogin(username, password);
        if(result.getCode()!= ErrorCode.SUCCESS){
            map.put(Constant.Static.MSG,result.getMsg());

            return Constant.AdminHtml.LOGIN;
        }else{
            session.setAttribute(Constant.Session.USER,result.getData());

            UserDTO userDTO=(UserDTO) CoventUtils.getApiResultData(result);

            if(Constant.UserRole.VISITOR.equals(userDTO.getUserRole())){
                return "redirect:../index.html";
            }else{
                return "redirect:../admin/index.html";
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
            session.setAttribute(Constant.Static.MSG,result.getMsg());

            return Constant.AdminHtml.REGISTER;
        }else{
            if(bool){
                return Constant.AdminHtml.LOGIN;
            }else{
                return Constant.AdminHtml.REGISTER;
            }
        }
    }

    @GetMapping("/mgr")
    public String userMgr(HashMap<String,Object> map){
        List<UserDTO> userDTOS=null;

        String keyword=(String)session.getAttribute(Constant.Session.KEYWORD);
        if(StringUtils.isNotEmpty(keyword)){
            userDTOS =(List<UserDTO>) CoventUtils.getApiResultData(userClient.getUserByKeyWord(keyword));

            session.removeAttribute(Constant.Session.KEYWORD);
        }else{
            userDTOS=(List<UserDTO>) CoventUtils.getApiResultData(userClient.getAllUser());
        }

        map.put(Constant.Session.USERS,userDTOS);

        return Constant.AdminHtml.USER;
    }

    @ResponseBody
    @GetMapping("/search")
    public Boolean userSearch(@RequestParam("keyword")String keyword,
                              HashMap<String,Object> map){
        List<UserDTO> userDTOS=(List<UserDTO>) CoventUtils.getApiResultData(userClient.getUserByKeyWord(keyword));

        if(userDTOS !=null){
            session.setAttribute(Constant.Session.KEYWORD,keyword);

            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public Boolean userAdd(@ModelAttribute UserDTO userDTO){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(userClient.addUser(userDTO));

        return bool;
    }

    @ResponseBody
    @GetMapping("/getUserById")
    public UserDTO getUserById(@RequestParam("id") String id){
        UserDTO userDTO=(UserDTO) CoventUtils.getApiResultData(userClient.getUserById(id));

        return userDTO;
    }

    @ResponseBody
    @PostMapping("/update")
    public Boolean userUpdate(@ModelAttribute UserDTO userDTO){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(userClient.updateUser(userDTO));

        return bool;
    }

    @ResponseBody
    @GetMapping("/delete")
    public Boolean userDelete(@RequestParam("id") String id){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(userClient.deleteUser(id));

        return bool;
    }

    @ResponseBody
    @GetMapping("/changeRole")
    public Boolean changeRole(@RequestParam("id") String id){
        Boolean bool=(Boolean) CoventUtils.getApiResultData(userClient.changeRole(id));

        return bool;
    }
}
