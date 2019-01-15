package com.cc.leetCode;

/**
 * 整数反转
 */
public class ReverseInt7 {
    /**
     * 借用long 方式
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {

        long r = 0;
        for (; x != 0; ) {
            int l = x % 10;
            x = x / 10;
            r = r * 10 + l;
        }
        if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) return 0;
        return (int) r;
    }

    /**
     * 不借用 long
     *
     * @param x
     * @return
     */
    public static int reverse1(int x) {

        int r = 0;
        for (; x != 0; ) {
            int l = x % 10;
            x = x / 10;
            if (r > Integer.MAX_VALUE/10 || (r == Integer.MAX_VALUE/10 && l > 7)){
                return 0;
            }
            if (r < Integer.MIN_VALUE/10 || (r == Integer.MIN_VALUE/10 && l > 8)){
                return 0;
            }
            r = r * 10 + l;
        }
        return  r;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1463847412));
    }
}
