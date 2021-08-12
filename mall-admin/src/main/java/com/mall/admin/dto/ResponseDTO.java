package com.mall.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author huangxiuqi
 */
@ApiModel("统一响应")
public class ResponseDTO<T> {

    @ApiModelProperty(value = "提示信息", example = "请求成功")
    private String message;

    @ApiModelProperty(value = "状态码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "响应数据", example = "{}")
    private T data;

    public ResponseDTO(T data) {
        this(200, "success", data);
    }

    public ResponseDTO(Integer code, String message) {
        this(code, message, null);
    }

    public ResponseDTO(Integer code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
