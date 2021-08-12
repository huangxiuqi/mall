package com.mall.admin.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记不对响应包装统一响应结构
 * 标记类时，此类下的所有方法的响应都不会包装
 * 标记方法时，此方法的响应不会包装
 * @author huangxiuqi
 * @createTime 2021/8/12 21:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface NotWrapResponse {
}
