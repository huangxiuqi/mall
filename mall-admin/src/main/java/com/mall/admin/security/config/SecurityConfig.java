package com.mall.admin.security.config;

import com.mall.admin.security.handler.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity配置
 * @author huangxiuqi
 * @createTime 2021/8/9 20:41
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 禁用CSRF，启用CORS
        http.csrf().disable().cors();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/api/*/login")
                .permitAll()
                .antMatchers("/v2/api-docs")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 修改用户名密码登录Url，注册认证成功/失败处理器
        http.formLogin()
                .loginProcessingUrl("/api/v1/login")
                .successHandler(new MallAuthenticationSuccessHandler())
                .failureHandler(new MallAuthenticationFailureHandler());

        // 修改退出登录Url，注册退出登录成功处理器
        http.logout()
                .logoutUrl("/api/v1/logout")
                .logoutSuccessHandler(new MallLogoutSuccessHandler());

        // 注册认证异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(new MallAuthenticationEntryPoint())
                .accessDeniedHandler(new MallAccessDeniedHandler());

        // 不创建session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }
}
