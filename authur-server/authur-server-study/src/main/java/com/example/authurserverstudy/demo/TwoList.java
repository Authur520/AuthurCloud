package com.example.authurserverstudy.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jibing.li
 * @Description
 * @date 2023/4/24 16:33
 */
public class TwoList {

    public static void main(String[] args) {
        List<User> list1 = new ArrayList<>();
        list1.add(new User(1, "张三"));
        list1.add(new User(2, "李四"));
        list1.add(new User(3, "王五"));
        List<User> list2 = new ArrayList<>();
        list2.add(new User(2, "李四"));
        list2.add(new User(3, "王五"));
        list2.add(new User(4, "赵六"));
        List<User> collect = list1.stream().filter(item1 ->
                list2.stream().anyMatch(item2 ->
                        item1.getId().equals(item2.getId()))
        ).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect);
        System.out.println(collect);

    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private Integer id;
    private String name;


}
