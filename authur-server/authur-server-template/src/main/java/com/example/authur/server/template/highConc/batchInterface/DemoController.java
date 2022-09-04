package com.example.authur.server.template.highConc.batchInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author authur
 * @description:Controller层做异步
 */
@RestController
@RequestMapping("/user")
public class DemoController {

    @Autowired
    BatchService batchService;

    @RequestMapping("/getUser")
    public Callable<String> DemoQuery(HttpServletRequest request) {
        long id = Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        System.out.println("线程："+name+"线程id"+id);
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                //调用service层（业务）
                String batch = batchService.Batch();
                long id = Thread.currentThread().getId();
                String name = Thread.currentThread().getName();
                System.out.println("线程："+name+"线程id"+id);
                return batch;
            }
        };
//        FutureTask demoTask = new FutureTask(callable);
        return callable;
    }

}