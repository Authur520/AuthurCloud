package com.example.authur.auth.configure;

import com.example.authur.common.configure.AuthurAuthExceptionConfigure;
import com.example.authur.common.handler.AuthurAccessDeniedHandler;
import com.example.authur.common.handler.AuthurAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/10/28 12:21
 */
@Configuration
@EnableResourceServer
public class AuthurResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthurAuthExceptionEntryPoint exceptionEntryPoint;

    @Autowired
    private AuthurAccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    //注入配置
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
