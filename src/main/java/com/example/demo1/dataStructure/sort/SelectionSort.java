package com.example.demo1.dataStructure.sort;

import java.util.Arrays;

/**
 * @author liangqing
 * @since 2021/2/7 14:28
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] b = {9, 5, 7, 3, 2, 1};
        selectSort(b);
    }

    public static void selectSort(int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return;
        }

        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            int min = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    minIndex = j;
                    min = a[j];
                }
            }
            if (minIndex != i) {
                a[minIndex] = a[i];
                a[i] = min;
            }

            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(a));
        }


    }
}
