package com.fdzang.microservice.blog.ucenter.common.dto;

import lombok.Data;

/**
 * @author tanghu
 * @Date: 2019/4/16 22:56
 */
@Data
public class LinkDTO {

    private String id;

    private String linkAddress;

    private String linkDescription;

    private Integer linkOrder;

    private String linkTitle;
}
