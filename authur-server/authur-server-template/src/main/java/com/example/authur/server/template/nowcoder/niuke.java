package com.example.authur.server.template.nowcoder;

import java.util.Scanner;

/**
 * @author authur
 * @description:
 */
public class niuke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            String verify = verify(new StringBuilder(str));
            System.out.println(verify);
        }
    }

    public static String verify(StringBuilder str){
        if (str.length() <=2){
            return str.toString();
        }
        int i = 0;
        while (i < str.length()){
            if (i+2 >= str.length()){
                break;
            }
            // AABCC
            if (str.charAt(i)==str.charAt(i+1)){
                if (str.charAt(i+1)==str.charAt(i+2)){
                    str.deleteCharAt(i+2);
                    continue;
                }else if (str.charAt(i+2) == str.charAt(i+3)){
                    str.deleteCharAt(i+3);
                    continue;
                }
            }
            i++;

//            if (str.charAt(i) != str.charAt(i+1)){
//                i++;
//                continue;
//            }
//
//            if (str.charAt(i+1) == str.charAt(i+2)){
//                str.deleteCharAt(i+2);
//                continue;
//            }
//            if (i+3>=str.length()){
//                break;
//            }

        }

        return str.toString();
    }

}
