package com.cc.leetCode;

/**
 *
 */
public class Palindrome9 {
    /**
     * 反转全部数字  要考虑溢出
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int y = x;
        long s = 0;
        while (y !=0){
            s = s*10 + y % 10;
            y = y/10;
        }
        if (s == x) return true;
        return false;
    }

    /**
     * 反转一半数字
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        //以0结尾的数会导致 x > reserve 判断时候 多一位
        if (x < 0 || (x%10 == 0 && x != 0)) return false;
        int reserve = 0;
        while (x > reserve){
            reserve = reserve*10 + x % 10;
            x = x/10;
        }
        return x == reserve || x == reserve/10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome1(1010));
    }
}
