package com.cc.leetCode;

/**
 * 最长回文数
 */
public class Palindromic5 {
    /**
     * 暴力遍历
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(s, j, s.length() - i + j - 1)) {
                    return s.substring(j, s.length() - i + j);
                }
            }
        }
        return null;
    }

    /**
     * 递归判断字符串是否是回文数
     *
     * @param s
     * @param start
     * @param end
     * @return
     */
    public static boolean isPalindrome(String s, int start, int end) {
        if (end <= start) {
            return true;
        }
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        } else {
            return isPalindrome(s, start + 1, end - 1);
        }
    }

    /**
     * 动态规划 由一个数 或两个相等的数进行扩展
     *
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        int max = 1, start = 0, end = 1;
        if (s.length() == 0) return "";
        for (int i = 0; i < s.length(); i++) {
            //单个元素初始化
            for (int j = 0; j <= i && j < s.length() - i; j++) {
                if (s.charAt(j + i) == s.charAt(i - j)) {
                    if (max < 2*j+1) {
                        max = 2*j+1;
                        start = i - j ;
                        end = j + i+1;
                    }
                }else {
                    break;
                }
            }
            //两个相同的元素初始化
            for (int j = 0; j <= i && j < s.length() - i-1; j++) {
                if (i + 1 < s.length()) {
                    if (s.charAt(i - j) == s.charAt(i + 1 + j)) {
                        if (max < 2*j+2) {
                            max = 2*j+2;
                            start = i - j ;
                            end = j + i + 2;
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        return s.substring(start,end );
    }

    /**
     * 中心点扩展
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        int max = 1, start = 0, end = 1;
        if (s.length() == 0) return "";
        for (int i = 0; i < s.length(); i++) {
            //单个元素初始化
            for (int j = 0; j <= i && j < s.length() - i; j++) {
                if (s.charAt(j + i) == s.charAt(i - j)) {
                    if (max < 2*j+1) {
                        max = 2*j+1;
                        start = i - j ;
                        end = j + i+1;
                    }
                }else {
                    break;
                }
            }
            //两个相同的元素初始化
            for (int j = 0; j <= i && j < s.length() - i-1; j++) {
                if (i + 1 < s.length()) {
                    if (s.charAt(i - j) == s.charAt(i + 1 + j)) {
                        if (max < 2*j+2) {
                            max = 2*j+2;
                            start = i - j ;
                            end = j + i + 2;
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        return s.substring(start,end );
    }

    public static void main(String[] args) {
        String s = "babada";
        System.out.println(longestPalindrome1(s));
    }
}
