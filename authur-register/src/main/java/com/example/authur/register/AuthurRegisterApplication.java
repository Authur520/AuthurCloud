package com.example.authur.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AuthurRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthurRegisterApplication.class, args);
    }

}
