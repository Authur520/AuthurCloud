package com.example.authur.server.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/2 17:22
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthurServerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthurServerSystemApplication.class, args);
    }
}
