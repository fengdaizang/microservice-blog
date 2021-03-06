package com.fdzang.microservice.blog.common.utils;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;

/**
 * @author tanghu
 * @Date: 2019/1/11 14:37
 */
public class CoventUtils {
    public static Object getApiResultData(ApiResult apiResult){
        if(apiResult==null){
            throw new BlogException(ErrorCode.DATA_NULL_ERROR,"远程连接失败！");
        }

        return apiResult.getData();
    }
}
