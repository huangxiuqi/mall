package com.mall.admin.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户无权限访问
 * @author huangxiuqi
 * @createTime 2021/8/9 21:00
 */
public class MallAccessDeniedHandler extends MallAbstractHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(MallAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // TODO 多语言
        sendResponse(response, HttpStatus.FORBIDDEN.value(), "无权访问");
    }
}
