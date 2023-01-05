package com.example.authur.server.template.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

        List<DeviceDataDto> list1 = new ArrayList<>();
        DeviceDataDto deviceDataDto = new DeviceDataDto();
        DeviceDataDto deviceDataDto1 = new DeviceDataDto();
        DeviceDataDto deviceDataDto2 = new DeviceDataDto();
        DeviceDataDto deviceDataDto3 = new DeviceDataDto();
        DeviceDataDto deviceDataDto4 = new DeviceDataDto();
        DeviceDataDto deviceDataDto5 = new DeviceDataDto();

        deviceDataDto.setAgentId(123.32);
        deviceDataDto1.setAgentId(123.45);
        deviceDataDto2.setAgentId(321.78);
        deviceDataDto3.setAgentId(31.45);
        deviceDataDto4.setAgentId(123.90);
        deviceDataDto5.setAgentId(12.3);

        list1.add(deviceDataDto);
        list1.add(deviceDataDto1);
        list1.add(deviceDataDto2);
        list1.add(deviceDataDto3);
        list1.add(deviceDataDto4);
        list1.add(deviceDataDto5);
        List<DeviceDataDto> deviceDataList = list1.stream().sorted(Comparator.comparing(DeviceDataDto::getAgentId).reversed()).collect(Collectors.toList());
        for (int i = 0; i < deviceDataList.size(); i++) {
            System.out.println(deviceDataList.get(i));
        }

    }
}
