package com.example.authur.server.template.idUtils.mysql;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author authur
 * @description: 多个数据库通过表去维护唯一主键id
 */
public class OnlyId {

    public long id(){
        /**
         * 1.设计表test_id：自增id，唯一字段stub
         * 2.如果字段stub存在为值'b'，id自增，存入b
         * 3.查询最后插入的id：select LAST_INSERT_ID
         */
        String sql = "replace into test_id<表> (stub<字段>) value('b')" +
                     "select LAST_INSERT_ID";
        String id = "select LAST_INSERT_ID";
        return Long.parseLong(id);

    }

}
