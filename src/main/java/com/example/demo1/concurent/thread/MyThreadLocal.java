package com.example.demo1.concurent.thread;

import java.util.concurrent.CountDownLatch;

public class MyThreadLocal {

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(10);
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      Thread thread = new Thread(() -> {
        ThreadLocal<Integer> threadLocal;
        for (int j = 0; j < 20; j++) {
          threadLocal = new ThreadLocal();
          threadLocal.set(finalI + j);
          System.out.println(threadLocal.get());
        }
        countDownLatch.countDown();

      });
      thread.start();

    }
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("当前线程");
  }


}
