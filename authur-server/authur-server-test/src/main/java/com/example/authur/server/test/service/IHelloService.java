package com.example.authur.server.test.service;

import com.example.authur.common.entity.AuthurServerConstant;
import com.example.authur.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/4 17:56
 */
@FeignClient(value = AuthurServerConstant.AUTHUR_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);

}
