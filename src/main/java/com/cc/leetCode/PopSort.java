package com.cc.leetCode;

/**
 * 冒泡
 */
public class PopSort {
    public static int[]  sort(int[] nums){

        for (int i = nums.length-1;i >= 0 ;i--){
            boolean flag = true;
            for (int j = 0;j < i;j ++){
                if (nums[j]>nums[j+1]){
                    int m = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = m;
                    flag = false;
                }
            }
            if (flag) break;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] n = {1, 2, 3, 4,7,6,54,4,3,6,7,8,3,4,5,6};
        int[] sort = sort(n);
        System.out.println();
    }
}
