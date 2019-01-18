package com.cc.leetCode;

/**
 * 盛水最多的
 */
public class MostWater11 {
    /**
     * 遍历法
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int most = 0;
        for (int i = 0 ; i < height.length ; i ++){
            for (int j = i +1 ;j < height.length ; j++){
                int x = (j - i) * Math.min(height[i], height[j]);
                most = most > x ? most : x;
            }
        }
        return most;
    }

    /**
     * 双指针法 (未证明)
     * @param height
     * @return
     */
    public static int maxArea1(int[] height) {
        int most = 0;
        int i = 0;
        int j = height.length -1;
        while (i < j){
            int i1 = (j - i) * Math.min(height[i], height[j]);
            most = most > i1 ? most : i1;
            if (height[i] < height[j]){
                i ++;
            }else {
                j --;
            }
        }
        return most;
    }

    public static void main(String[] args) {
        int[] arg =  {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea1(arg));
    }
}
