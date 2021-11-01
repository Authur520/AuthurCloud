package com.example.authur.common.handler;

import com.example.authur.common.AuthurAuthException;
import com.example.authur.common.entity.AuthurResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

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
}
