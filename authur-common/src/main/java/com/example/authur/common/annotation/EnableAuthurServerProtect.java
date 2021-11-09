package com.example.authur.common.annotation;

import com.example.authur.common.configure.AuthurServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 16:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthurServerProtectConfigure.class)
public @interface EnableAuthurServerProtect {
}
