package com.fdzang.microservice.blog.web.controller;

import com.fdzang.microservice.blog.common.exception.BlogException;
import com.fdzang.microservice.blog.common.exception.ErrorCode;
import com.fdzang.microservice.blog.common.framework.ApiResult;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public abstract class BaseController {
    
    private static final String TOKEN_HEADER = "X-G7-Altair-User-Console-Token";
    
	@Autowired
	private HttpServletRequest request;

	protected ApiResult ok(long code, String msg, Object data) {
		ApiResult vo = new ApiResult();
		vo.setCode(code);
		vo.setData(data);
		vo.setMsg(msg);
		return vo;
	}

	protected ApiResult ok(long code, String msg) {
		return ok(code, msg, null);
	}

	protected ApiResult ok(Object obj) {
		return ok(0L, null, obj);
	}

	protected ApiResult ok() {
		return ok(0L, null, null);
	}

	protected ApiResult fail(String msg) {
		return ok(ErrorCode.SYSTEM_ERROR, msg, null);
	}

	protected ApiResult fail(long code, String msg) {
        return ok(code, msg, null);
    }

	/**
	 * 统一异常处理
	 */
	@ExceptionHandler
	public ApiResult unifiedExceptionHandler(Exception ex) {
		log.error("BaseController record error:{}", ex);
		if (ex instanceof BlogException) {
			BlogException aex = (BlogException) ex;
		    return fail(aex.getCode(), aex.getMessage());
		}
		return fail("服务器错误！" + ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ApiResult parameterValidationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		String message;
		if (constraintViolations == null) {
			message = ex.getMessage();
		} else {
			List<String> errorMsg = new ArrayList<>(constraintViolations.size());
			for (ConstraintViolation<?> violation : constraintViolations) {
				errorMsg.add(violation.getMessage());
			}
			message = Joiner.on("; ").skipNulls().join(errorMsg);
		}
		log.error("BaseController record parameterValidationException:{}", ex);
		return fail("参数错误！" + message);
	}

//	protected UserDTO getCurrentUser() {
//	    String token = request.getHeader(TOKEN_HEADER);
//	    UserDTO user = tokenService.checkToken(token);
//		if(user==null){
//			throw new BlogException(ErrorCode.TOKEN_EXPIRE, "请重新登录");
//		}
//		if(user.getId() == null || user.getId() == 0){//沙箱环境第一使用用户缓存信息
//			tokenService.refreshToken(token,user.getUsername());
//			user = tokenService.checkToken(token);
//		}
//		return user;
//	}
//
//	protected String getToken() {
//        String token = request.getHeader(TOKEN_HEADER);
//        return token;
//    }
}
