package com.fdzang.microservice.blog.common.framework;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kchen
 * @date   2017/10/19
 */

@Slf4j
public class BaseController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult exceptionHandler(HttpServletRequest req, Exception e) {
        ApiResult result = new ApiResult();
        if(e instanceof BlogException){
            BlogException ex=(BlogException)e;
            result.setCode(ex.getCode());
            result.setMsg(e.getMessage());
        }else {
            result.setCode(500L);
            result.setMsg(e.getMessage());
        }
        log.warn("exceptionHandler, uri:{}", req.getRequestURI(), e);
        return result;
    }

    @ExceptionHandler(value = {HttpMessageConversionException.class, MethodArgumentNotValidException.class, ServletRequestBindingException.class})
    @ResponseBody
    public ApiResult parameterExceptionHandler(HttpServletRequest req, Exception e) {
        ApiResult result = new ApiResult();
        if(e instanceof BlogException){
            BlogException ex=(BlogException)e;
            result.setCode(ex.getCode());
            result.setMsg(e.getMessage());
        }else {
            result.setCode(500L);
            result.setMsg("参数错误");
        }
        log.warn("parameterExceptionHandler, uri:{}", req.getRequestURI(), e);
        return result;
    }

    public ApiResult ok(Object data) {
        ApiResult result = new ApiResult();
        result.setCode(0L);
        result.setMsg("ok");
        result.setData(data);

        return result;
    }

    public ApiResult ok() {
        ApiResult result = new ApiResult();
        result.setCode(0L);
        result.setMsg("ok");
        return result;
    }

    protected ApiResult fail(String msg) {
        return fail(ErrorCode.ALTAIR_CONFIG_FAIL, msg);

    }

    protected ApiResult fail(long code, String msg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}