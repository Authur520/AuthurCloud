package com.example.authur.server.template.highConc.batchInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/8/24 12:59
 */
@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    BatchService batchService;

    @RequestMapping("/getUser")
    public void getUser() throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            final String id = String.valueOf(100 + i);
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.countDown();
                    countDownLatch.await();
                    Map<String, Object> map = batchService.queryBatch(id);
//                    System.out.println("接口返回结果"+ map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

    }

}
