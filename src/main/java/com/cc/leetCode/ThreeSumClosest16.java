package com.cc.leetCode;

import java.util.Arrays;
import java.util.Set;

public class ThreeSumClosest16 {
    //暴力遍历
    public static int threeSumClosest(int[] nums, int target) {
        int gap = Integer.MAX_VALUE;
        int result = 0;
        if (nums.length <3) return 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                for (int k = j; k < nums.length; k++) {
                    if (i != j && i != k && k != j ) {
                        int sum = nums[i] + nums[j] + nums[k] ;
                        int s = Math.abs(sum - target);
                        if (gap > s){
                            gap = s;
                            result = sum;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 双指针
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length -2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum - target < 0){
                    l++;
                }else if (sum - target > 0){
                    r--;
                }else {
                    return sum;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] param = {0,2,1,-3};
        System.out.println(threeSumClosest1(param, 1));
    }
}
