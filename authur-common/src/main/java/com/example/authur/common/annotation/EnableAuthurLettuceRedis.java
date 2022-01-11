package com.example.authur.common.annotation;

import com.example.authur.common.configure.AuthurLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/11 12:28
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthurLettuceRedisConfigure.class)
public @interface EnableAuthurLettuceRedis {
}
