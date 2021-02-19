package com.example.demo1.dataStructure.algorithm;

import java.util.Arrays;

/**
 * @author liangqing
 * @since 2021/2/19 9:36
 */
public class KmpAlgorithm {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(kpmNext("ABCDABD")));


        String original = "BBC ABCDAB ABCDABCDABDE";
        String dest = "ABCDABD";
        System.out.println(kmpSearch(original, dest, kpmNext(dest)));

    }

    private static int kmpSearch(String original, String dest, int[] next) {
        for (int i = 0, j = 0; i < original.length(); i++) {

            while (j > 0 && original.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (original.charAt(i) == dest.charAt(j)) {
                j++;
            }

            if (j == dest.length()) {
                //找到了
                return i - j + 1;
            }
        }
        return -1;

    }

    private static int[] kpmNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }

            next[i] = j;
        }
        return next;
    }
}
