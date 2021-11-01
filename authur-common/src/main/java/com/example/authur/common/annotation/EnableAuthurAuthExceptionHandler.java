package com.example.authur.common.annotation;

import com.example.authur.common.configure.AuthurAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/1 15:49
 */

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthurAuthExceptionConfigure.class)
public @interface EnableAuthurAuthExceptionHandler {
}
