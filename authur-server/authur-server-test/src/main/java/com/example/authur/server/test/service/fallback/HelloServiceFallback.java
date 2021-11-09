package com.example.authur.server.test.service.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.example.authur.server.test.service.IHelloService;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/4 18:19
 */
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
    @Override
    public IHelloService create(Throwable throwable) {
        return name -> {
            log.error("调用Authur-Server-System服务出错", throwable);
            return "调用出错";
        };
    }

}
