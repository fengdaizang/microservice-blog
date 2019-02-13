package com.fdzang.microservice.blog.ucenter.common.dto;

import lombok.Data;

/**
 * @author tanghu
 * @Date: 2019/1/8 9:51
 */
@Data
public class UserDTO {

    private String id;

    private String userEmail;

    private String password;

    private String userName;

    private String userUrl;

    private String userRole;

    private Integer userArticleCount;

    private Integer userPublishedArticleCount;

    private String userAvatar;
}
