package com.example.authur.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/19 11:10
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:authur-server-system.properties"})
@ConfigurationProperties(prefix = "authur.server.system")
public class AuthurServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private AuthurSwaggerProperties swagger = new AuthurSwaggerProperties();
}
