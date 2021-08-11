package com.mall.admin.swagger.descriptor;

import com.mall.admin.dto.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringSecurity登录接口Swagger描述
 * 实际功能由SpringSecurity实现
 * @author huangxiuqi
 */
@Api(tags = "用户认证")
@RestController
public class LoginController {

    @ApiOperation(value = "用户登录", response = ResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, example = "admin", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, example = "123456a", dataTypeClass = String.class)
    })
    @PostMapping(value = "/api/v1/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void login(String username, String password) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @ApiOperation(value = "用户退出登录", response = ResponseDTO.class)
    @GetMapping("/api/v1/logout")
    public void logout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
