package com.example.demo1.leetcode.array;

/**
 * @author liangqing
 * @since 2020/12/2 11:43
 * <p>
 * 动态规划常见题目
 * https://leetcode-cn.com/leetbook/read/dynamic-programming-1-plus/xch21e/
 * 1 爬楼梯
 * 2 最小花费爬楼梯
 * 3 打家劫舍
 * <p>
 * <p>
 * 粗暴解法   表格法   https://juejin.cn/post/6844904181187215368
 */
public class dtgh {


    public static void main(String[] args) {
        System.out.println(one(10));
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(minCostClimbingStairs(a));
        System.out.println(rob(a));
    }

    /**
     * 1 爬楼梯
     * 问题：有n阶的楼梯，每次你可以爬1或2个台阶。你有多少种方法可以爬到楼顶？
     * 分析：这个应该属于最简单的动态规划问题了，基本上有一定数学基础都能写出来。首先我们来分析一下：
     * 第i阶楼梯可以由以下两种方法得到：
     * <p>
     * 在第(i - 1)阶后向上爬1阶。
     * 在第(i - 2)阶后向上爬2阶。
     */
    public static int one(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] a = new int[n + 1];

        a[1] = 1;
        a[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[n];
    }

    /**
     * 问题：数组的每个索引作为一个阶梯，
     * 第i个阶梯对应着一个非负数的体力花费值cost[i](索引从0开始)。
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     * 求到达楼顶的最低花费。(开始时，你可以选择从索引为0或1的元素作为初始阶梯)
     * 分析：这个问题在爬楼梯的基础上面加了一个体力值的消耗
     * ，所以它不再是简单的找到所有路径，而是找出这些路径中花费体力最小的路径，
     * 如果我们从整体来说可能无从下手，总不能把所有路径的花费值都算出来比较大小吧。
     * 这个时候我们需要将问题划分为子问题，从子问题中归纳出整体的解。分析步骤如下：
     * 我们假设有i个阶梯，数组用nums表示(数组下标从0开始，所以这里我们让i也从0开始)；
     * <p>
     * 当i=0时，花费最小体力值nums[0];
     * 当i=1时，花费最小体力值nums[1];
     * 当i=2时，有两种情况：
     * <p>
     * 当nums[0] > nums[1]时，最小花费体力为nums[0] + nums[2];
     * 当nums[0] < nums[1]时，最小花费体力为nums[1] + nums[2];
     * <p>
     * f(i) = min(f(i-1), f(i -2))+nums[i]
     */
    public static int minCostClimbingStairs(int[] cost) {

        int length = cost.length;

        if (length == 1) {
            return cost[0];
        } else if (length == 2) {
            return cost[0];
        }
        int[] dp = new int[length];

        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[length - 1], dp[length - 2]);
    }

    /**
     * 打家劫舍
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        } else if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[length];

    }
}
