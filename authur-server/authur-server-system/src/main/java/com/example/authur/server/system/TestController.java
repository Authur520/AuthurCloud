package com.example.authur.server.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/2 17:35
 */
@RestController
public class TestController {
    @GetMapping("info")
    public String test(){
        return "febs-server-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
