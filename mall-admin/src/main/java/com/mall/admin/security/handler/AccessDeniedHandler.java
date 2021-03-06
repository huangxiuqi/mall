package com.mall.admin.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户无权限访问
 * @author huangxiuqi
 * @createTime 2021/8/9 21:00
 */
public class AccessDeniedHandler extends AbstractHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        sendResponse(response, HttpStatus.FORBIDDEN, accessDeniedException.getLocalizedMessage());
    }
}
