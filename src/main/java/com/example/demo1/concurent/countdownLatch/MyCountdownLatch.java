package com.example.demo1.concurent.countdownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MyCountdownLatch$
 *
 * @author shuai
 * @date 2020/5/14$
 */

@Slf4j
public class MyCountdownLatch {

    public static final int NUM = 100;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        CountDownLatch countStartDownLatch = new CountDownLatch(1);

        for (int i = 0; i < NUM; i++) {
            MyRunnable myRunnable = new MyRunnable("lq".concat(String.valueOf(i)), countDownLatch,
                    countStartDownLatch);

            executorService.execute(myRunnable);
        }

        countDownLatch.await();

        countStartDownLatch.countDown();
    }

}

@Slf4j
class MyRunnable implements Runnable {

    private final String name;
    private final CountDownLatch countDownLatch;
    private final CountDownLatch countStartDownLatch;

    public MyRunnable(String name, CountDownLatch countDownLatch,
                      CountDownLatch countStartDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.countStartDownLatch = countStartDownLatch;
    }

    @Override
    public void run() {
        log.info("#############################{}进入众能{}", name, Thread.currentThread().getId());
        countDownLatch.countDown();
        try {
            countStartDownLatch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        log.info("*****************************{}开始为众能做贡献{}", name,
                Thread.currentThread().getId());
    }
}