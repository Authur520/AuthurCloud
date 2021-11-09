package com.example.authur.common.selector;

import com.example.authur.common.AuthurAuthException;
import com.example.authur.common.configure.AuthurOAuth2FeignConfigure;
import com.example.authur.common.configure.AuthurServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description: ImportSelector导入注解
 * @Author: jibing.Li
 * @Date: 2021/11/9 17:01
 */
public class AuthurCloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                AuthurAuthException.class.getName(),
                AuthurOAuth2FeignConfigure.class.getName(),
                AuthurServerProtectConfigure.class.getName()
        };
    }
}
