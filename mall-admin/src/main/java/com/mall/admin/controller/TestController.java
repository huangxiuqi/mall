package com.mall.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxiuqi
 * @createTime 2021/8/9 21:04
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasAuthority('super_admin')")
    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }

    @RequestMapping("/test2")
    public String test2() {
        return "test2";
    }
}
