package com.mall.admin.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码编码器
 * @author huangxiuqi
 * @createTime 2021/8/9 22:41
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        // 指定密码散列编码器
        return new BCryptPasswordEncoder();
    }
}
