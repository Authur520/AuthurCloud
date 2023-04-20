package com.example.authur.server.template.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jibing.li
 * @Description
 * @date 2023/1/6 14:04
 */
@Component
public class TestTask {

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void test(){


    }


}
