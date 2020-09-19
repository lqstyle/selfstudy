package com.example.demo1.concurent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * mySychronized$
 *
 * @author shuai
 * @date 2020/5/13$
 */
@Slf4j
public class MySychronized {


  private synchronized  static void objectLock() {
    int a=0;
    int b=1;
    int c=a/b;
    log.info(Thread.currentThread().getId() + "业务数据对象锁"+c);
  }

  public static void main(String[] args) throws Exception {
    Object o= new Object();
    synchronized (o){
      int a=0;
      int b=1;
      int c=a/b;
      log.info(Thread.currentThread().getId() + "业务数据对象锁"+c);


    }
    //对象锁
    MySychronized mySychronized = new MySychronized();
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        synchronized (mySychronized) {
          objectLock();
          log.info(String.valueOf(Thread.currentThread().getId()));
          try {
            Thread.sleep(1000 * 5);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    executorService.submit(new Runnable() {
      @Override
      public void run() {
        synchronized (mySychronized) {
          objectLock();
          log.info(String.valueOf(Thread.currentThread().getId()));
          try {
            Thread.sleep(1000 * 5);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

  }

}