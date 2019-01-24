package com.cc.leetCode;

/**
 * 快排
 */
public class QuickSort {
    public static int[] sort(int[] nums) {
        quick(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quick(int[] nums, int start, int end) {
        if (start >= end) return;

        int partition = partition(nums, start, end);
        quick(nums, start, partition - 1);
        quick(nums, partition + 1, end);
    }

    /**
     * 求中间位置  start -i 小于目标值得集合 i- end 未处理的集合
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] nums, int start, int end) {
        int target = end;
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] < nums[target]) {
                if (i != j) {
                    int num = nums[i];
                    nums[i] = nums[j];
                    nums[j] = num;
                }
                i++;
            }
        }
        int num = nums[i];
        nums[i] = nums[target];
        nums[target] = num;
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] sort = sort(nums);
        System.out.println("");
    }
}