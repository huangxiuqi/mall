package com.mall.admin.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理，例如未认证异常
 * @author huangxiuqi
 * @createTime 2021/8/9 20:58
 */
public class AuthenticationEntryPoint extends AbstractHandler implements org.springframework.security.web.AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof InsufficientAuthenticationException) {
            // 用户未认证，返回401
            sendResponse(response, HttpStatus.UNAUTHORIZED.value(), authException.getLocalizedMessage());
        } else {
            sendResponse(response, HttpStatus.BAD_REQUEST.value(), authException.getLocalizedMessage());
        }
    }
}
