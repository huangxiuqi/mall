package com.mall.admin.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户认证成功处理器
 * @author huangxiuqi
 * @createTime 2021/8/9 20:56
 */
public class MallAuthenticationSuccessHandler extends MallAbstractHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(MallAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // TODO 多语言，返回Token
        sendResponse(response, 200, "登录成功");
    }
}
