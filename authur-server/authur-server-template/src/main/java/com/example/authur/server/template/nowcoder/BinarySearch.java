package com.example.authur.server.template.nowcoder;

import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author authur
 * @description: 二分查找升序数组下标
 */
public class BinarySearch {

    public static int search (int[] nums, int target) {
        // write code here
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = (l + r)/2;
            if(nums[m] == target){
                return m;
            }
            if(nums[m] > target)
                r = m -1;
            else
                l = m + 1;

        }
        return -1;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InterruptedException {
//        int nums[] = {-1,0,3,4,6,10,13,14};
//        int search = search(nums, 13);
//        if (search != -1) System.out.println("下标："+ search);
//            else System.out.println("没有找到下标");

        // 泛型的类型擦除
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(12);
        Class<? extends ArrayList> aClass = integers.getClass();
        Method add = aClass.getMethod("add", Object.class);
        Object invoke = add.invoke(integers, "aa");
        System.out.println(integers);

        final StringBuffer stringBuffer = new StringBuffer("12");
        stringBuffer.append("34");
        System.out.println("Stringbuffer:"+stringBuffer);

        // String和new String的区别
        String s = "hello"; //先从实例池中查，如果有，把地址赋值给s，如果没有就在实例池中创建对象，然后赋值
        String s1 = new String("hello"); //先从实例池中查，如果有，不需要创建实例池中的对象，在堆中创建对象，把堆中的地址赋值给s1。
                                                 //             如果没有，在实例池中创建对象，在堆中创建对象，把堆中的地址赋值给s1。
        System.out.println(s == s1); //false

        System.out.println();
        for (int i = 0; i < 5; i++) {
            new ReentrantLock().lock();
            new ReentrantLock().lock();
            Thread.sleep(50000);
        }

        //finally里return语句会把try catch中的return语句覆盖掉。
        // sleep和wait
        // sleep等待的时候不会释放锁，wait会释放对象锁，当使用notify的时候会重新获取锁进入运行状态
        // wait只能用在同步方法、代码块中，sleep随处

    }


}
