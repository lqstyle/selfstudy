package com.example.demo1.dataStructure.sort;

import java.util.Arrays;

/**
 * @author liangqing
 * @since 2021/2/8 9:33
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] a = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellsort(a);
    }

    public static void shellsort(int[] a) {

        int temp = 0;
        int count = 0;
        //最外层循环，分组
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            //内层组内循环
            for (int i = gap; i < a.length; i++) {
                // 如果当前元素大于加上步长后的那个元素，说明交换
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (a[j] > a[j + gap]) {
                        temp = a[j];
                        a[j] = a[j + gap];
                        a[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(a));
        }
    }


    public static void shellsort2(int[] a) {

        int count = 0;
        //最外层循环，分组
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            //内层组内循环
            for (int i = gap; i < a.length; i++) {
                int j = i;
                int temp = a[j];
                if (a[j] < a[j - gap]) {
                    while (j - gap >= 0 && temp < a[j - gap]) { //移动
                        a[j] = a[j - gap];
                        j -= gap;
                    }
                    //当退出 while 后，就给 temp 找到插入的位置
                    a[j] = temp;
                }
            }
        }
    }
}
