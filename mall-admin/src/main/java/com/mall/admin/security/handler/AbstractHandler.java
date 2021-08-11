package com.mall.admin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

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
     * @param response
     * @param status
     * @param message
     * @throws IOException
     */
    protected void sendResponse(HttpServletResponse response, Integer status, String message) throws IOException {
        Map<String, Object> responseBody = new HashMap<>();

        responseBody.put("code", status);
        responseBody.put("message", message);

        response.setStatus(status);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
