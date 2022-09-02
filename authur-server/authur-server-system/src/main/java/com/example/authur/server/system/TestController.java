package com.example.authur.server.system;

import com.example.authur.common.entity.system.TradeLog;
import com.example.authur.server.system.service.ITradeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2021/11/2 17:35
 */
@RestController
public class TestController {

    @Autowired
    ITradeLogService iTradeLogService;

    @GetMapping("info")
    public String test(){
        return "authur-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name){
        return "hello" + name;
    }

    @GetMapping("pay")
    public void pay(TradeLog tradeLog){
        iTradeLogService.orderAndPay(tradeLog);
    }

    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i < 100; i++) {
                threadPool.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "    ok");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }



    }
    

}
