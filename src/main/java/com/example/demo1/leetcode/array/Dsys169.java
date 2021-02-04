package com.example.demo1.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangqing
 * @since 2020/12/15 17:16
 * <p>
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 方法1  ：hash
 * 方法2 排序 众数
 */
public class Dsys169 {

    /**
     * hash 法
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int threshold = length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int s : nums) {
            if (map.containsKey(s)) {
                int time = map.get(s);
                map.put(s, ++time);
                if (time > threshold) {
                    return s;
                }
            } else {
                map.put(s, 1);
                if (1 > threshold) {
                    return s;
                }
            }
        }
        return 0;
    }

    /*
    中位数法
     */
    public static int twoMajorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法
     *
     * 如果相同就加1
     * 如果不同就-1
     * 如果还存在就是众数
     * @param nums
     * @return
     */
    public static int threeMajorityElement(int[] nums) {
        int i = 0, result = 0;
        for (int num : nums) {
            if (i == 0) {
                result = num;
            }
            if (num == result) {
                ++i;
            } else {
                --i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 1};
//        System.out.println(majorityElement(nums));
//        System.out.println(twoMajorityElement(nums));
        System.out.println(threeMajorityElement(nums));

    }
}


