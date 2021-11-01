package com.example.authur.common.configure;

import com.example.authur.common.handler.AuthurAccessDeniedHandler;
import com.example.authur.common.handler.AuthurAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;


/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/1 15:35
 */
public class AuthurAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public AuthurAccessDeniedHandler accessDeniedHandler(){
        return new AuthurAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authExceptionEntryPoint")
    public AuthurAuthExceptionEntryPoint authExceptionEntryPoint(){
        return new AuthurAuthExceptionEntryPoint();
    }

}
