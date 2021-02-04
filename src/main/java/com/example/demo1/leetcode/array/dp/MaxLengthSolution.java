package com.example.demo1.leetcode.array.dp;

/**
 * @author liangqing
 * @since 2020/12/3 17:37
 *
 * 动态规划
 *
 */
public class MaxLengthSolution {

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubsequence(s1, s2));
    }
    public static int longestCommonSubsequence(String text1, String text2) {

        int s1 = text1.length();
        int s2 = text2.length();

        int[][] dp = new int[s1+1][s2+1];
        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();

        for (int i = 1; i <= s1; i++) {
            for (int j = 1; j <= s2; j++) {
                if (a[i-1]== b[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s1][s2];
    }
}
