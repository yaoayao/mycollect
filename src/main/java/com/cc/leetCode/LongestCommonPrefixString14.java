package com.cc.leetCode;

/**
 * 最长公共子串
 */
public class LongestCommonPrefixString14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String result = "";
        for (int j = 0; j < strs[0].length();j ++){
            result = result + strs[0].charAt(j);
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(result)||strs[i].length() <= j) {
                    return result.substring(0,result.length() -1);
                }
            }
        }
        return result;
    }

    /**
     *  二分法
     * @param strs
     * @return
     */
    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            if(str.length() < minLen){
                minLen = str.length();
            }
        }
        int low = 1, max = minLen;
        int mid = 0;
        while (low < max){
            mid = (low + max)/2;
            if (isCommon(strs,mid)){
                low++;
            }else {
                max --;
            }
        }
        return strs[0].substring(0,mid);
    }
    public static boolean isCommon(String[] strs,int len){
        String substring = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(substring)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] s = {"flower","flow","f"};
        System.out.println(longestCommonPrefix(s));
    }
}
