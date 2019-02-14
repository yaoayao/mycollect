package com.cc.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileComb17 {
    public static List<String> letterCombinations(String digits) {
        Map<Integer, List<Character>> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        if ("".equals(digits)) return result;
        int start = 91;
        for (int i = 2; i < 10; i++) {
            List<Character> list = new ArrayList<>();
            list.add((char) (start + 3 * i));
            list.add((char) (start + 1 + 3 * i));
            list.add((char) (start + 2 + 3 * i));
            if (i == 7 || i == 9) {
                list.add((char) (start + 3 + 3 * i));
                start++;
            }
            map.put(i, list);
        }
        inner(map, digits, "", result);
        return result;
    }

    public static void inner(Map<Integer, List<Character>> map, String digits, String item, List<String> result) {
        if ("".equals(digits) && !"".equals(item)) {
            result.add(item);
            return;
        }
        char c = digits.charAt(0);

        for (Character character : map.get(c - 48)) {
            inner(map, digits.substring(1), item + character, result);
        }
    }

    public static void main(String[] args) {
        List<String> strings = letterCombinations("");
        System.out.println('0' + 0);
    }
}
