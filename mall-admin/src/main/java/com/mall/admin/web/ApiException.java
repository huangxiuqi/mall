package com.mall.admin.web;

import org.springframework.http.HttpStatus;

/**
 * 主动抛出异常，可用于异常响应
 * @author huangxiuqi
 * @createTime 2021/8/12 21:50
 */
public class ApiException extends RuntimeException {

    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ApiException(HttpStatus status, String message, Throwable e) {
        super(message, e);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
