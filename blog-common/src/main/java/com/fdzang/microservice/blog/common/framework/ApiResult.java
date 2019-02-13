package com.fdzang.microservice.blog.common.framework;

import lombok.Data;

@Data
public class ApiResult<T> {
    private Long code;
    private String msg;
    private String message;
    private T data;
}
