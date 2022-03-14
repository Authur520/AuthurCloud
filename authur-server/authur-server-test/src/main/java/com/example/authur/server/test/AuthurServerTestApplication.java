package com.example.authur.server.test;

import com.example.authur.common.annotation.AuthurCloudApplication;
import com.example.authur.common.annotation.EnableAuthurAuthExceptionHandler;
import com.example.authur.common.annotation.EnableAuthurOauth2FeignClient;
import com.example.authur.common.annotation.EnableAuthurServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/2 17:22
 */
@EnableFeignClients
@SpringBootApplication
@AuthurCloudApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthurServerTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthurServerTestApplication.class, args);
    }
}
