package com.example.demo1.dataStructure.recursive;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangqing
 * @since 2021/2/6 9:27
 * <p>
 * 递归就是方法自己调用自己,每次调用时传入不同的变量.
 * 递归有助于编程者解决复杂的问题,同时 可以让代码变得简洁
 */
public class RecursiveDemo {
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        outPut(10);
    }

    /**
     * 输出
     * @param n
     */
    public static void outPut(Integer n) {
        atomicInteger.incrementAndGet();
        System.out.println("方法被调用了" + atomicInteger.get());
        if (n > 1) {
            outPut(n - 1);
        }
        System.out.println(n);
    }


    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
