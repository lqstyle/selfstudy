package com.example.demo1.dataStructure.search;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangqing
 * @since 2021/2/5 9:54
 * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应 mid 处开始查找
 * 由二分查找 公式推断出来
 * int mid = left + (right – left) * (findVal – arr[left]) / (arr[right] – arr[left])
 */
public class InsertValueSearch {

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(insertValueSearch(arr, 0, 9, 8));
        System.out.println("总共查找了" + atomicInteger.get());
    }

    public static int insertValueSearch(int[] a, int left, int right, int num) {
        atomicInteger.incrementAndGet();

        //需要考虑找不到的情况
        if (left > right) {
            return -1;
        }
        //获取中间位置
        int middle = left + (right - left) * (num - a[left]) / (a[right] - a[left]);
        if (num > a[middle]) {
            return insertValueSearch(a, middle + 1, right, num);
        } else if (num < a[middle]) {
            return insertValueSearch(a, left, middle - 1, num);
        } else {
            //返回位置，最终返回的结果都在此处
            return middle;
        }
    }
}
