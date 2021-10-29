package com.example.authur.gateway;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: 关闭Security的csrf功能
 * @Author: jibing.Li
 * @Date: 2021/10/29 16:30
 */

@EnableWebSecurity
public class AuthurGatewayWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
