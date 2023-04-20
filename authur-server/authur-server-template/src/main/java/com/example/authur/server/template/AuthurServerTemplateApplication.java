package com.example.authur.server.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AuthurServerTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthurServerTemplateApplication.class, args);
    }

}
