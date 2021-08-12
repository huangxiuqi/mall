package com.mall.admin.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mall.admin.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 在返回响应外包裹统一响应结构
 * @author huangxiuqi
 * @createTime 2021/8/12 21:00
 */
@ControllerAdvice(basePackages = "com.mall.admin")
public class ResponseBodyWrapAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ResponseBodyWrapAdvice.class);

    private static final String MEDIA_TYPE = "json";

    private final ObjectMapper objectMapper;

    public ResponseBodyWrapAdvice() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class converterType) {
        return !returnType.getDeclaringClass().isAnnotationPresent(NotWrapResponse.class)
                && !returnType.hasMethodAnnotation(NotWrapResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        try {
            // 将响应结果包在ResponseDTO中
            if (!(body instanceof ResponseDTO)) {
                if (body instanceof String && !selectedContentType.getType().contains(MEDIA_TYPE)) {
                    // String类型因为使用StringHttpMessageConverter，需要将结果转成字符串，否则会报错
                    body = objectMapper.writeValueAsString(new ResponseDTO<>(body));
                } else {
                    body = new ResponseDTO<>(body);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("序列化失败，请求路径：" + request.getURI().getPath(), e);
        }
        return body;
    }
}
