package com.example.authur.server.template.nowcoder;

/**
 * @author authur
 * @description: 回文数字
 */
public class Palindrome {

    public static void main(String[] args) {
        int x = 313;
        boolean palindrome = isPalindrome(x);
        if (palindrome) {
            System.out.println(x + "这是一个回文数字");
        }else {
            System.out.println(x + "这不是一个回文数字");
        }

    }

    static boolean isPalindrome(int x ) {
        if(x < 0) return false;
        int div = 1;
        while(x / div >= 10){
            div *=10;
        }

        while(x > 0){
            int left = x / div;
            int right = x % 10;
            if(left != right) return false;
            //去除首位之后的数字
            int y = x % div;
            // 去除尾数之后的数字的新x
            x = y / 10;
            // div也得随之减少两位
            div /= 100;
        }
        return true;
    }

}
