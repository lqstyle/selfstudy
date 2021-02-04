package com.example.demo1.algorithm.order;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author liangqing
 * @since 2020/12/24 17:55
 */
public class MyCompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture completableFutur = new CompletableFuture<>();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i <100 ; i++) {
            final CompletableFuture<String> uCompletableFuture = completableFutur.supplyAsync(() -> {
                System.out.println("当前线程: " + Thread.currentThread().getId());
                countDownLatch.countDown();
                return StringUtils.EMPTY;
            });

        }
        countDownLatch.await();
        System.out.println();

    }
}
