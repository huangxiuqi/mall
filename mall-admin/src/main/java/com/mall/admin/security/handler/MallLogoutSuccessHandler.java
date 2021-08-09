package com.mall.admin.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户退出登录成功处理器
 * @author huangxiuqi
 * @createTime 2021/8/9 20:58
 */
public class MallLogoutSuccessHandler extends MallAbstractHandler implements LogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(MallLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        log.info(authentication.toString());
        //TODO 多语言
        sendResponse(response, 200, "退出登录成功");
    }
}
