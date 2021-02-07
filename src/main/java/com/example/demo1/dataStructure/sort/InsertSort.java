package com.example.demo1.dataStructure.sort;

import java.util.Arrays;

/**
 * @author liangqing
 * @since 2021/2/7 15:38
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
    }

    public static void insertSort(int[] a) {
        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < a.length; i++) {
            insertVal = a[i];
            insertIndex = i - 1;

            // 给 insertVal 找到插入的位置 // 说明
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < a[insertIndex]) {
                a[insertIndex + 1] = a[insertIndex];
                insertIndex--;
            }

            // 当退出 while 循环时，说明插入的位置找到, insertIndex + 1
            // 举例：理解不了，我们一会 debug //这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                a[insertIndex + 1] = insertVal;
            }
            System.out.println("第" + i + "轮插入");
            System.out.println(Arrays.toString(a));

        }

    }
}
