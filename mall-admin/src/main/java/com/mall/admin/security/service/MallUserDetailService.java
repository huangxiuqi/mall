package com.mall.admin.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户查询服务
 * 根据用户名查询用户
 * @author huangxiuqi
 * @createTime 2021/8/9 22:36
 */
@Component
public class MallUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public MallUserDetailService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 数据库查询用户
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "aaa");
        return new User("admin", passwordEncoder.encode("123456a"), authorities);
    }
}
