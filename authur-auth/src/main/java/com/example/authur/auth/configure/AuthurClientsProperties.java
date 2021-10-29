package com.example.authur.auth.configure;

import lombok.Data;

/**
 * @Description: Security配置类
 * @Author: jibing.Li
 * @Date: 2021/10/29 16:51
 */
@Data
public class AuthurClientsProperties {
    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
