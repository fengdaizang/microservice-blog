package com.fdzang.microservice.blog.console.dto;

import com.fdzang.microservice.blog.ucenter.common.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tanghu
 * @Date: 2019/1/21 9:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTokenDTO {
    private String token;
    private UserDTO user;
}
