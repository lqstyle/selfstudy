package com.example.demo1.leetcode.array;

/**
 * @author liangqing
 * @since 2020/12/22 14:42
 *
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 */
public class Leetcode670 {

    public static int maximumSwap(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        int maxIdx = chars.length - 1;
        int[] maxArr = new int[chars.length];
        for (int i = chars.length - 1; i >= 0  ; i--) {
            if (chars[i] > chars[maxIdx]) {
                maxIdx = i;
            }
            maxArr[i] = maxIdx;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[maxArr[i]] != chars[i]) {
                char temp = chars[maxArr[i]];
                chars[maxArr[i]] = chars[i];
                chars[i] = temp;
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        int num =325391;
        System.out.println(maximumSwap(num));
    }
}
