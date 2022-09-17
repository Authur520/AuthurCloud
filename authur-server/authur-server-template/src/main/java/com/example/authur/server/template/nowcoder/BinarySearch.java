package com.example.authur.server.template.nowcoder;

import org.springframework.stereotype.Service;

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

    public static void main(String[] args) {
        int nums[] = {-1,0,3,4,6,10,13,14};
        int search = search(nums, 13);
        if (search != -1) System.out.println("下标："+ search);
            else System.out.println("没有找到下标");
    }


}
