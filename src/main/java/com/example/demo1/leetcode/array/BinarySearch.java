package com.example.demo1.leetcode.array;

/**
 * @author liangqing
 * @since 2020/12/22 17:53
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(binarySearch(a, 7));
        System.out.println(binarySearch(a, 2));
    }

    private static int binarySearch(final int[] a, final int data) {
        int low = 0;
        int height = a.length;

        while (low <= height) {
            //查找中间位置，此处需要在循环中
            int middle = low + (height - low) / 2;
            if (a[middle] < data) {
                low = middle + 1;
            } else if (a[middle] == data) {
                return middle;
            } else {
                height = middle - 1;
            }
        }
        return -1;
    }
}
