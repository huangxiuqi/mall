package com.mall.admin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author huangxiuqi
 * @createTime 2021/8/11 21:19
 */
@Component
public class MessageSourceUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    public static MessageSource getMessageSource() {
        if (context == null) {
            synchronized (MessageSourceUtils.class) {
                if (context == null) {
                    try {
                        MessageSourceUtils.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return context;
    }

    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        return getMessageSource().getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object[] args) {
        return getMessageSource().getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        synchronized (MessageSourceUtils.class) {
            context = applicationContext;
            MessageSourceUtils.class.notifyAll();
        }
    }
}
