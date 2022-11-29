package com.example.authur.server.template.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jibing.li
 * @Description
 * @date 2022/11/24 18:23
 */
public class Stream {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("one", "two", "three", "two");
        //集合过滤
        List<String> collect = list.stream().filter(f -> !"one".equals(f)).collect(Collectors.toList());
        //集合过滤去重
        List<String> collect1 = list.stream().filter(f -> !"one".equals(f)).distinct().collect(Collectors.toList());
        //跳过n个元素
        List<String> collect2 = list.stream().skip(2).collect(Collectors.toList());
        //获取几个元素
        List<String> collect3 = list.stream().limit(3).collect(Collectors.toList());
        System.out.println(collect3);

    }
}
