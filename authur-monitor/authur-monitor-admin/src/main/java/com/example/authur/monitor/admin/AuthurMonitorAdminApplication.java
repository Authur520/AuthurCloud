package com.example.authur.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AuthurMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthurMonitorAdminApplication.class, args);
    }

}
