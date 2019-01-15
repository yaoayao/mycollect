package com.cc.leetCode;

import org.apache.commons.lang.text.StrBuilder;

import java.util.ArrayList;

/**
 * Z 转换
 */
public class ZConvert6 {
    /**
     * 按行访问
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (s.length() == 0) return s;
        if (numRows == 0|| numRows == 1) return s;
        StringBuilder result = new StringBuilder();
        int n = 2 * numRows - 2;
        int last = s.length() % n;
        int m = s.length() / n;
        for (int i = 0; i < numRows; i++) {
            if (last !=0){
                m++;
            }
            for (int j = 0 ; j < m;j ++){
                if ( i == 0){
                    if (j * n <= s.length() -1)
                        result.append( s.charAt(j * n));
                }else if (i > 0 && i <numRows-1){
                    if (j * n + i <= s.length() -1)
                        result.append( s.charAt(j * n + i));
                    if (j * n + i + 2*(numRows-i -1) <= s.length() -1)
                        result.append( s.charAt(j * n + i + 2*(numRows-i -1)));
                }else {
                    if ( j * n + i <= s.length() -1)
                        result.append( s.charAt(j * n + i));
                }
            }

        }
        return result.toString();
    }

    /**
     *按行排序 从头遍历s 将属于不同行的字符添加到不同的list里面，最后遍历list
     * @param s
     * @param numRows
     * @return
     */
    public static String convert1(String s, int numRows) {
        ArrayList<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i ++) {
            list.add(new StringBuilder());
        }
        int curRow = 0;
        int c = 1;
        for (int i = 0 ;i < s.length();i ++){
            list.get(curRow).append(s.charAt(i));
            if (curRow == numRows -1){
                c = -1;
            }else if (curRow == 0){
                c = 1;
            }
            curRow = curRow +c;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
            result.append(stringBuilder);
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String s = "AB";
        System.out.println(convert1(s, 1));
    }
}
