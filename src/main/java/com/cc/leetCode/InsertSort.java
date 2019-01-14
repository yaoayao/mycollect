package com.cc.leetCode;

/**
 * 插入排序
 */
public class InsertSort {
    public static int[] sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = i-1; j >= 0; j--) {
                if (nums[j] > num){
                    nums[j+1] = nums[j];
                }else {
                    nums[j+1] = num;
                    break;
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 1,3333,43,567,2};
        sort(a);
        System.out.println("");
    }
}
