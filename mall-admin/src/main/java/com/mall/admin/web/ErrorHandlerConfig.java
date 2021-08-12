package com.mall.admin.web;

import com.mall.admin.dto.ResponseDTO;
import com.mall.admin.utils.MessageSourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * 统一异常处理
 * @author huangxiuqi
 * @createTime 2021/8/12 21:59
 */
@ControllerAdvice(basePackages = "com.mall.admin")
public class ErrorHandlerConfig implements ApplicationContextAware {
    
    private static final Logger log = LoggerFactory.getLogger(ErrorHandlerConfig.class);

    private ApplicationContext context;

    /**
     * 使用ApiException主动抛出异常
     */
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> apiException(ApiException e) {
        ResponseDTO<String> dto = new ResponseDTO<>(e.getStatus().value(), e.getMessage(), null);
        return new ResponseEntity<>(dto, e.getStatus());
    }

    /**
     * 控制器参数校验异常
     * 校验@Valid注解标记的参数时抛出的异常
     */
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        ResponseDTO<Object> dto = new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                MessageSourceUtils.getMessage("ErrorHandlerConfig.invalidParameter"));

        // 多个错误只取第一条
        if (errors.size() > 0) {
            dto = new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errors.get(0).getDefaultMessage());
        }
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    /**
     * 参数异常
     * Spring Assert断言的异常
     */
    @ResponseBody
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleException(IllegalArgumentException e) {
        StackTraceElement[] stackTraces = e.getStackTrace();

        ResponseDTO<Object> dto = new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                MessageSourceUtils.getMessage("ErrorHandlerConfig.invalidParameter"));
        if (stackTraces.length > 0) {
            StackTraceElement lastTrace = stackTraces[0];
            if (Objects.equals(lastTrace.getClassName(), Assert.class.getName())) {
                dto = new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            }
        }
        log.error("参数异常", e);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST
        );
    }

    /**
     * 剩余的所有异常
     * 统一返回500错误
     */
    @ResponseBody
    @ExceptionHandler({Exception.class, BindException.class})
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("exception error:\n", e);
        String message = MessageSourceUtils.getMessage("ErrorHandlerConfig.internalServerError");

        // 开发环境展示详细错误
        String env = "dev";
        if (Objects.equals(context.getEnvironment().getActiveProfiles()[0], env)) {
            message = e.getMessage();
        }
        ResponseDTO<Object> dto = new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
