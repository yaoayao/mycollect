package com.cc.leetCode;

/**
 * 选择排序
 */
public class SelectSort {
    public static int[] sort(int[] num){
        for (int i = 0; i < num.length ;i ++){
            int min = i;
            for (int j = i +1;j < num.length  ; j ++){
                if (num[j] < num[i]){
                    min = j;
                }
            }
            //减少交换次数
            if (min != i){
                int i1 = num[i];
                num[i] = num[min];
                num[min] = i1;
            }

        }
        return num;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 2};
        sort(num);
        System.out.println("");
    }
}
