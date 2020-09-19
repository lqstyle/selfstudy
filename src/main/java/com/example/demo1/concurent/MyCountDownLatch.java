package com.example.demo1.concurent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * MyCountDownLatch$
 *
 * @author shuai
 * @date 2020/4/28$
 * <p>
 * 实现100台汽车赛跑
 */
@Slf4j
public class MyCountDownLatch {


  public static void main(String[] args) {
    Integer i = 100;
    //赛车计时器
    CountDownLatch start = new CountDownLatch(i);

    CountDownLatch end = new CountDownLatch(i);

    //到达终点
    CountDownLatch stopCountDownLatch = new CountDownLatch(1);

    log.info("赛车比赛{}:", Thread.currentThread().getName());
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int j = 0; j < i; j++) {
      executorService.submit(new Car(start, i, stopCountDownLatch,end));
    }
    log.info("预备");
    try {
      start.await();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    stopCountDownLatch.countDown();
    log.info("赛车达到终点");

  }

  private static void bet() {
    return;
  }

}

@Slf4j
class Car implements Runnable {

  private CountDownLatch start;

  private CountDownLatch stopCountDownLatch;

  private CountDownLatch end;

  Integer number = 0;

  public Car(CountDownLatch start, Integer n, CountDownLatch stopCountDownLatch,CountDownLatch end) {
    this.start = start;
    this.number = n;
    this.stopCountDownLatch = stopCountDownLatch;
    this.end = end;
  }

  @Override
  public void run() {
    log.info("赛车比赛准备   {}:", Thread.currentThread().getName());
    start.countDown();
    try {
      stopCountDownLatch.await();
      log.info("赛车{}出发，耗时 {}:", Thread.currentThread().getName(), System.currentTimeMillis());
      long begin = System.currentTimeMillis();
      Thread.sleep(Integer.parseInt(String.valueOf(Math.random())));
      long end = System.currentTimeMillis();
      log.info("耗时：{}", end-begin);

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }


}



