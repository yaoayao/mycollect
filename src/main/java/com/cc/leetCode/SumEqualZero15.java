package com.cc.leetCode;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

/**
 * 三数之和 为0
 */
public class SumEqualZero15 {
    /**
     * 先快排 再分为两部分 正数和负数
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;
        //快排
        QuickSort.sort(nums);

        int lessEnd = 0;
        int moreStart = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <0){
                lessEnd ++ ;
            }else if (nums[i] >0){
                break;
            }
            moreStart ++;
        }

        List<Integer> items = new ArrayList<>();
        if (moreStart - lessEnd >=3){
            items.add(0);
            items.add(0);
            items.add(0);
            result.add(items);
        }
        HashSet<String> unique = new HashSet<>();
        for (int i =0;i <lessEnd;i ++){
            for (int j = moreStart;j < nums.length; j ++){
                int aim = HalfSearch.search(nums, 0 - (nums[i] + nums[j]));
                if (aim != -1 && aim != i && aim != j){
                    items = new ArrayList<>();
                    if (aim < i){
                        String s = nums[aim] + "-" + nums[i] + "-" + nums[j];
                        if (!unique.contains(s)){
                            items.add(nums[aim]);
                            items.add(nums[i]);
                            items.add(nums[j]);
                            unique.add(s);
                            result.add(items);
                        }
                    }else if (aim > i && aim< j){
                        String s = nums[i] + "-" + nums[aim] + "-" + nums[j];
                        if (!unique.contains(s)){
                            items.add(nums[i]);
                            items.add(nums[aim]);
                            items.add(nums[j]);
                            unique.add(s);
                            result.add(items);
                        }

                    }else {
                        String s = nums[i] + "-" + nums[j] + "-" + nums[aim];
                        if (!unique.contains(s)){
                            items.add(nums[i]);
                            items.add(nums[j]);
                            items.add(nums[aim]);
                            unique.add(s);
                            result.add(items);
                        }
                    }

                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] t = {0,0,0};
        List<List<Integer>> lists = threeSum(t);
        System.out.println("");
    }
}
