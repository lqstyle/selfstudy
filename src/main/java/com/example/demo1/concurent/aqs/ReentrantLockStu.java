package com.example.demo1.concurent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockStu {

  public static void main(String[] args) {

    ReentrantLock reentrantLock = new ReentrantLock();

    ExecutorService executorService = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());

    for (int i = 0; i <5 ; i++) {
      executorService.execute(()->{
        reentrantLock.lock();
        try {
          System.out.println("当前线程"+Thread.currentThread());
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          reentrantLock.unlock();
        }
      });
    }
    System.out.println("结束");
  }

}
