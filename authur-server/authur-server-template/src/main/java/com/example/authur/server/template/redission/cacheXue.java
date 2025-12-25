package com.example.authur.server.template.redission;

import com.example.authur.common.service.RedisService;

import java.time.Duration;
import java.util.List;

/**
 * @author authur
 * @description:
 */
public class cacheXue {

    public Object findProductCategory(){
        String key = "productCategoryKey";

        Object obj = new RedisService().get(key);
        if (obj != null){
            synchronized (this){
                obj = new RedisService().get(key);
                if (obj != null){
                    return obj;
                }
                Object o = new Object();
                Duration expire = Duration.ofHours(2L).plus(Duration.ofSeconds((int) (Math.random() * 100)));
                new RedisService().set(key,o,expire.getSeconds());
            }
        }
        return null;
    }
}
