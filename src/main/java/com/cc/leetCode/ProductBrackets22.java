package com.cc.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成括号
 */
public class ProductBrackets22 {
    /**
     * 闭合数法 待研究
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0){
            result.add("");
        }
        for (int i = 0; i < n; i++) {
            for (String left :generateParenthesis(i)){
                for (String right : generateParenthesis(n - 1 - i)) {
                    result.add("(" + left + ")" + right);
                }
            }
        }
        return result;
    }

    /**
     * 回溯法
     * 对于括号的组合，要考虑其有效性。比如说，)(， 它虽然也是由一个左括号和
     * 一个右括号组成，但它就不是一个有效的括号组合。 那么，怎样的组合是有效的呢？
     * 对于一个左括号，在它右边一定要有一个右括号与之配对， 这样的才能是有效的。
     * 所以，对于一个输出，比如说(()())， 从左边起，取到任意的某个位置得到的串，
     * 左括号数量一定是大于或等于右括号的数量， 只有在这种情况下，这组输出才是有效的。
     * 我们分别记左，右括号的数量为left和right， 如下分析可看出，(()())是个有效的括号组合。
     *
     * 这样一来，在程序中，只要还有左括号，我们就加入输出串，然后递归调用。 当退出递归时，如果剩余的右括号数量比剩余的左括号数量多，我们就将右括号加入输出串。 直到最后剩余的左括号和右括号都为0时
     * @param n
     * @return
     */
    public static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        make("", n, n, result);
        return result;
    }
    public static void make(String s ,int left ,int right,List<String> result){
        if (left == 0 && right == 0){
            result.add(s);
        }
        if (left > 0){
            make(s + "(", left -1, right, result);
        }
        if (right > left){
            make(s + ")", left, right - 1, result);
        }
    }

    public static void main(String[] args) {
        List<String> strings = generateParenthesis1(3);
        System.out.println("----");
    }
}
