package com.cc.leetCode;

/**
 * 字符串转整数
 */
public class AutoI8 {
    public static int myAtoi(String str) {
        int result = 0;
        boolean signFlag = true;
        boolean startFlag = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' '){
                if (c >= '0' && c <= '9' ){
                    startFlag = false;

                    if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && c - 48 > 7)){
                        return Integer.MAX_VALUE;
                    }
                    if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && c - 48 > 8)){
                        return Integer.MIN_VALUE;
                    }
                    if (!signFlag){
                        result = result * 10 - c + 48;
                    }else {
                        result = result * 10 + c - 48;
                    }

                }else if (startFlag && (c =='-'||c == '+')){
                    startFlag = false;
                    if (c == '-'){
                        signFlag = false;
                    }
                }else {
                    break;
                }
            }else if (!startFlag){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("  42"));
        System.out.println('0' + 0);
    }

}
