package com.example.demo1.dataStructure.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangqing
 * @since 2021/2/5 9:37
 * 前提条件： 二分查找的数据必须是有序的
 * 通过递归调用实现
 * 需要考虑退出的条件
 */
public class BinarySearch {

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        int arr[] = {1, 9, 9, 9, 9, 11, 22, 34, 89};
//        System.out.println(binarySearch(arr, 0, arr.length - 1, 9));
        System.out.println(binarySearchAll(arr, 0, arr.length - 1, 9));
        System.out.println("总共查找了" + atomicInteger.get() + "次");
    }

    /**
     * 二分查找，只有一个数的情况
     *
     * @param a
     * @param left
     * @param right
     * @param num
     * @return
     */
    public static int binarySearch(int[] a, int left, int right, int num) {
        atomicInteger.incrementAndGet();

        //需要考虑找不到的情况
        if (left > right) {
            return -1;
        }
        //获取中间位置
        int middle = (left + right) / 2;
        if (num > a[middle]) {
            return binarySearch(a, middle + 1, right, num);
        } else if (num < a[middle]) {
            return binarySearch(a, left, middle - 1, num);
        } else {
            //返回位置，最终返回的结果都在此处
            return middle;
        }
    }

    /**
     * 查找所有有效的数据 多个数的情况
     *
     * @param a
     * @param left
     * @param right
     * @param num
     * @return
     */
    public static List<Integer> binarySearchAll(int[] a, int left, int right, int num) {
        atomicInteger.incrementAndGet();

        //需要考虑找不到的情况
        if (left > right) {
            return new ArrayList<>();
        }
        //获取中间位置
        int middle = (left + right) / 2;
        int temp;
        if (num > a[middle]) {
            return binarySearchAll(a, middle + 1, right, num);
        } else if (num < a[middle]) {
            return binarySearchAll(a, left, middle - 1, num);
        } else {
            //返回位置，最终返回的结果都在此处
            List<Integer> result = new ArrayList<>();
            result.add(middle);
            temp = middle - 1;
            //先向左侧查找
            while (temp > left && a[temp] == a[middle]) {
                result.add(temp);
                temp--;
            }

            //再向右侧查找
            temp = middle + 1;
            while (temp < right && a[temp] == a[middle]) {
                result.add(temp);
                temp++;
            }
            return result;
        }
    }
}
