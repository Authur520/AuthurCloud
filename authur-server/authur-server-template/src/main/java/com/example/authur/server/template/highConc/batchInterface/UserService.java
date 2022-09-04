package com.example.authur.server.template.highConc.batchInterface;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author authur
 * @description:
 */
@Service
public class UserService {
    public List<Map<String, Object>> getUser(List<Map<String,Object>> users){
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String creteTime = format.format(date);
            map.put("creteTime", creteTime);
            double random = Math.random()*1000;
            long id = new Double(random).longValue();
            map.put("userId", users.get(i).get("id"));
            map.put("serialNo", users.get(i).get("serialNo"));
//            System.out.println("jieguo："+ map);
            list.add(map);
        }
        return list;
    }
}
