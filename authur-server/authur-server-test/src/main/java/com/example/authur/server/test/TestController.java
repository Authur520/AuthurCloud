package com.example.authur.server.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.authur.server.test.service.IHelloService;
import java.security.Principal;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/2 17:35
 */
@RestController
public class TestController {

    @Autowired
    private IHelloService iHelloService;

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1(){
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2(){
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name){
        return this.iHelloService.hello(name);
    }
}
