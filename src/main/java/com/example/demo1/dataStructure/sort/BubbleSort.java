package com.example.demo1.dataStructure.sort;

import java.util.Arrays;

/**
 * @author liangqing
 * @since 2021/2/7 12:01
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = new int[100000];
        for (int i = 0; i < 100000; i++) {
            a[i] = (int) (Math.random() * 10);
        }
        long e = System.currentTimeMillis();
//        int[] b = {9, 5, 7, 3, 2, 1};
        bubble(a);
        long s = System.currentTimeMillis();
        System.out.println((s - e) / 1000);
    }

    public static void bubble(final int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return;
        }

        boolean flag = Boolean.FALSE;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    flag = Boolean.TRUE;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }

            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(a));
            if (!flag) {
                break;
            }
        }
    }
}
