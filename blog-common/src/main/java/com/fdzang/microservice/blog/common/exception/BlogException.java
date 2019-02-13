package com.fdzang.microservice.blog.common.exception;


public class BlogException extends RuntimeException {
    private long code;

    public BlogException(long code, String message) {
        super(message);
        this.code = code;
    }

    public BlogException(long code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public long getCode() {
        return this.code;
    }
}
