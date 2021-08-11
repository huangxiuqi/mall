package com.mall.admin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangxiuqi
 * @createTime 2021/8/9 21:20
 */
public abstract class AbstractHandler {

    private final ObjectMapper objectMapper;

    public AbstractHandler() {
        objectMapper = new ObjectMapper();
    }

    /**
     * 发送JSON响应，统一响应格式
     * @param response HttpServletResponse
     * @param status HTTP状态码
     * @param message 提示消息
     * @throws IOException 异常
     */
    protected void sendResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        Map<String, Object> responseBody = new HashMap<>(4);

        responseBody.put("code", status.value());
        responseBody.put("message", message);

        response.setStatus(status.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
