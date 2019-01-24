package com.cc.leetCode;

import java.math.BigDecimal;

/**
 * 二分查找
 */
public class HalfSearch {
    public static int search(int[] nums,int s){
        return devide(nums, 0, nums.length - 1, s);
    }

    public static int devide(int[] nums, int start, int end, int s) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == s) return mid;
        if (nums[mid] < s) start = mid +1;
        if (nums[mid] > s) end = mid - 1;
        return devide(nums, start, end, s);
    }

    public static void main(String[] args) {
        int[] s = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(s,1));
    }
}
