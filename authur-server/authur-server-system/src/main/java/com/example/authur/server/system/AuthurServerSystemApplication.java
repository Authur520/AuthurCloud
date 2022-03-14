package com.example.authur.server.system;

import com.example.authur.common.annotation.AuthurCloudApplication;
import com.example.authur.common.annotation.EnableAuthurServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/2 17:22
 */
@EnableFeignClients
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAuthurServerProtect
@AuthurCloudApplication
@EnableTransactionManagement
@MapperScan("com.example.authur.server.system.mapper")
public class AuthurServerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthurServerSystemApplication.class, args);
    }
}
