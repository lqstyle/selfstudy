package com.example.demo1.dataStructure.search;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangqing
 * @since 2021/2/5 9:32
 */
public class SeqSearch {

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        seqSearch(arr, 89);
    }

    public static int seqSearch(int[] a, int num) {

        for (int i = 0; i < a.length; i++) {
            atomicInteger.incrementAndGet();
            if (a[i] == num) {
                System.out.println("遍历次数" + atomicInteger.get());
                return i;
            }
        }
        System.out.println("遍历次数" + atomicInteger.get());
        return -1;
    }
}
