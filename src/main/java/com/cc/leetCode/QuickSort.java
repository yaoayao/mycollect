package com.cc.leetCode;

/**
 * 快排
 */
public class QuickSort {
    public static int[] sort(int[] nums) {
        quick(nums,0,nums.length -1);
        return nums;
    }

    public static void quick(int[] nums, int start, int end) {
        if (start >= end) return ;
        int mid = end;
        for (;start < end;start ++){
            if (mid > start && nums[start] > nums[mid] || (mid < start && nums[start] < nums[mid])){
                int num = nums[start];
                nums[start]  = nums[mid];
                nums[mid] = num;
                mid = start;
            }
        }
        quick(nums, start, mid-1);
        quick(nums, mid + 1, end);
    }

    /**
     * 求中间位置  start -i 小于目标值得集合 i- end 未处理的集合
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static  int partition(int[] nums,int start,int end){
        int target = end;
        int i = start;
        for (int j = start;j < end;j ++){
            if (nums[i] < nums[target]){
                if (i != j){
                    int num = nums[i];
                    nums[i]  = nums[j];
                    nums[j] = num;
                }
                i ++;
            }
        }
        int num = nums[i];
        nums[i]  = nums[target];
        nums[target] = num;
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3, 2,45,3,4,6,8,9,34,2};
        int[] sort = sort(nums);
        System.out.println("");
    }
}