package com.example.authur.auth.configure;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: 写成可配置的文件
 * @Author: jibing.Li
 * @Date: 2021/10/29 17:09
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:authur-auth.properties"})
@ConfigurationProperties(prefix = "authur.auth")
public class AuthurAuthProperties {

    private AuthurClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
}
