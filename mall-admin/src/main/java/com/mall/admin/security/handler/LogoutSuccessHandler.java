package com.mall.admin.security.handler;

import com.mall.admin.utils.MessageSourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户退出登录成功处理器
 * @author huangxiuqi
 * @createTime 2021/8/9 20:58
 */
public class LogoutSuccessHandler extends AbstractHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(LogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        if (authentication != null) {
            log.info(authentication.toString());
        }
        sendResponse(response, HttpStatus.OK, MessageSourceUtils.getMessage("LogoutSuccess"));
    }
}
