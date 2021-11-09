package com.example.authur.common.annotation;

import com.example.authur.common.selector.AuthurCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/9 17:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthurCloudApplicationSelector.class)
public @interface AuthurCloudApplication {
}
