package com.example.authur.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthurAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthurAuthApplication.class, args);
    }

}
