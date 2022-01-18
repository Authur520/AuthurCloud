package com.example.authur.common.handler;

import com.example.authur.common.AuthurAuthException;
import com.example.authur.common.entity.AuthurResponse;
import com.example.authur.common.exception.AuthurException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/1 16:33
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthurResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new AuthurResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = AuthurAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthurResponse handleAuthurAuthException(AuthurAuthException e) {
        log.error("系统错误", e);
        return new AuthurResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)//todo ?
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthurResponse handleAccessDeniedException(){
        return new AuthurResponse().message("没有权限访问该资源");
    }

    @ExceptionHandler(value = AuthurException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthurResponse handleAuthurException(AuthurException e) {
        log.error("系统错误", e);
        return new AuthurResponse().message(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return AuthurResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthurResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new AuthurResponse().message(message.toString());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return AuthurResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthurResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new AuthurResponse().message(message.toString());
    }
}
