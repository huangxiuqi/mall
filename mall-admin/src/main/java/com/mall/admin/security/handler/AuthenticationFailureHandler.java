package com.mall.admin.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户认证失败处理器
 * @author huangxiuqi
 * @createTime 2021/8/9 20:57
 */
public class AuthenticationFailureHandler extends AbstractHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 认证失败返回原因
        sendResponse(response, HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
    }
}
