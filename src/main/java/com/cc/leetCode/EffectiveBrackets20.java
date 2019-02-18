package com.cc.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效的括号
 */
public class EffectiveBrackets20 {
    /**
     * 和栈的思想很类似
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.add(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.size() == 0) return false;
                Character remove = stack.remove(stack.size() - 1);
                char t = '1';
                switch (c) {
                    case ')':
                        t = '(';
                        break;
                    case ']':
                        t = '[';
                        break;
                    case '}':
                        t = '{';
                        break;
                }
                if (remove != t) {
                    return false;
                }
            }
        }
        if (stack.size() > 0) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }
}
