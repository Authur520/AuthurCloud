package com.example.authur.common.annotation;

import com.example.authur.common.configure.AuthurOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 14:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthurOAuth2FeignConfigure.class)
public @interface EnableAuthurOauth2FeignClient {
}
