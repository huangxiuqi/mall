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
 * 用户认证成功处理器
 * @author huangxiuqi
 * @createTime 2021/8/9 20:56
 */
public class AuthenticationSuccessHandler extends AbstractHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // TODO 返回Token
        sendResponse(response, HttpStatus.OK, MessageSourceUtils.getMessage("LoginSuccess"));
    }
}
