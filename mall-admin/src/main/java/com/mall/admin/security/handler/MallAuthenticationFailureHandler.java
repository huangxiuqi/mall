package com.mall.admin.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户认证失败处理器
 * @author huangxiuqi
 * @createTime 2021/8/9 20:57
 */
public class MallAuthenticationFailureHandler extends MallAbstractHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(MallAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 认证失败返回原因
        sendResponse(response, HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage());
    }
}