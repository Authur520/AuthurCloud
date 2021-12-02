package com.example.authur.auth;

import com.example.authur.common.annotation.EnableAuthurAuthExceptionHandler;
import com.example.authur.common.annotation.EnableAuthurServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthurAuthExceptionHandler
@EnableAuthurServerProtect
@MapperScan("com.example.authur.auth.mapper")
public class AuthurAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthurAuthApplication.class, args);
    }

}
