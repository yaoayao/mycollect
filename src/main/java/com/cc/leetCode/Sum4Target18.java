package com.cc.leetCode;

import java.util.*;

/**
 * 四数之和
 */
public class Sum4Target18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<String> strings = new HashSet<>();
        if (nums.length < 4) return result;
        if (target >= 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= target) {
                    for (int j = i + 1; j < nums.length; j++) {
                        int sum1 = nums[i] + nums[j];
                        if (sum1 <= target) {
                            for (int k = j + 1; k < nums.length; k++) {
                                int sum2 = sum1 + nums[k];
                                if (sum2 <= target) {
                                    for (int l = k + 1; l < nums.length; l++) {
                                        if (sum2 + nums[l] == target) {
                                            String setString = nums[i] + "-" + nums[j] + "-" + nums[k] + "-" + nums[l];
                                            if (!strings.contains(setString)) {
                                                List<Integer> itemList = new ArrayList<>();
                                                itemList.add(nums[i]);
                                                itemList.add(nums[j]);
                                                itemList.add(nums[k]);
                                                itemList.add(nums[l]);
                                                strings.add(setString);
                                                result.add(itemList);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } else {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] >= target) {
                    for (int j = i - 1; j >= 0; j--) {
                        int sum1 = nums[i] + nums[j];
                        if (sum1 >= target) {
                            for (int k = j - 1; k >= 0; k--) {
                                int sum2 = sum1 + nums[k];
                                if (sum2 >= target) {
                                    for (int l = k - 1; l >= 0; l--) {
                                        if (sum2 + nums[l] == target) {
                                            String setString = nums[i] + "-" + nums[j] + "-" + nums[k] + "-" + nums[l];
                                            if (!strings.contains(setString)) {
                                                List<Integer> itemList = new ArrayList<>();
                                                itemList.add(nums[i]);
                                                itemList.add(nums[j]);
                                                itemList.add(nums[k]);
                                                itemList.add(nums[l]);
                                                strings.add(setString);
                                                result.add(itemList);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        return result;
    }

    /**
     * 循环两个 加双指针
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        int length = nums.length;
        if (length < 4) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i=0; i<length-3; i++) {
            if (i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            if (nums[i]*4 > target) {
                break;
            }
            if (nums[i] + nums[length-1]*3 < target) {
                continue;
            }

            int n1 = nums[i];
            for (int j=i+1; j<length-2; j++) {
                if (j>i+1 && nums[j]==nums[j-1]) {
                    continue;
                }
                if (nums[i]+nums[j]*3 > target) {
                    break;
                }
                if (nums[i]+nums[j]+nums[length-1]*2 < target) {
                    continue;
                }

                int n2 = nums[j];
                int sum0 = n1 + n2;

                int l = j+1;
                if (sum0 == target && nums[l]>0) {
                    break;
                }

                int r = length-1;
                while (r > l) {
                    int n3 = nums[l];
                    int n4 = nums[r];
                    int sum = sum0 + n3 + n4;
                    if (sum == target) {
                        result.add(Arrays.asList(n1, n2, n3, n4));

                        l++;
                        r--;
                        while (r>l && nums[l]==nums[l-1]) {
                            l++;
                        }
                        while (r>l && nums[r]==nums[r+1]) {
                            r--;
                        }
                    } else if (sum > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[] nums = {1, -2, -5, -4, -3, 3, 3, 5};
        //[1,-2,-5,-4,-3,3,3,5]
        //-11
        List<List<Integer>> lists = fourSum(nums, -11);
        System.out.println("" + 2 + 1);
    }
}
