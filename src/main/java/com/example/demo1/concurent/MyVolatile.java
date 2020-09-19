package com.example.demo1.concurent;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;


/*
1. abortPolicy 直接抛异常，如果达到了最大线程数和阻塞队列的和(如有空余线程，则不会抛异常，若没有空余线程，则异常)，则抛出异常，主线程断掉
2. callerRunsPolicy  调用者运行，如果线程池的线程，不足以执行此任务，则把任务回退给调用者，让调用者运行
3.discardPolicy 抛弃当前运行的任务，不抛出异常，如果允许丢失，此方案最好
4. discardOldestPoicy 抛弃等待最久的任务，把当前任务加入队列再尝试重新提交,若放不下，则抛弃
 */
class Increase {

  private volatile int rentIncrease;

  public void increase(CountDownLatch countDownLatch) throws InterruptedException {
    System.out.println(Thread.currentThread().getName());
    rentIncrease++;
    Thread.sleep(1000);
    countDownLatch.countDown();
  }

  /*  private AtomicInteger rentIncrease = new AtomicInteger(0);

    public void increase(CountDownLatch countDownLatch) {
      System.out.println(Thread.currentThread().getId());
      rentIncrease.getAndIncrement();
      countDownLatch.countDown();
    }

  */
  public int getRentIncrease() {
    return rentIncrease;
  }
}

public class MyVolatile {

  public static void main(String[] args)
      throws InterruptedException, IllegalAccessException, InstantiationException {

    System.out.println("主程序" + Thread.currentThread().getName());

    System.out.println("____________________" + Runtime.getRuntime().availableProcessors());
 /*   ExecutorService executorService = new ThreadPoolExecutor(10, 13, 1, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory()
        , new AbortPolicy());*/
    ExecutorService executorService = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory()
        , new CallerRunsPolicy());
   /* ExecutorService executorService = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory()
        , new DiscardPolicy());*/

/*
    ExecutorService executorService = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory()
        , new DiscardOldestPolicy());
*/

    /*    ExecutorService executorService = Executors.newCachedThreadPool();*/
    CountDownLatch countDownLatch = new CountDownLatch(15);
    Increase increase = new Increase();
    for (int i = 0; i < 15; i++) {
      executorService.submit(() -> {
        try {
          increase.increase(countDownLatch);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });

    }

    executorService.shutdown();
    countDownLatch.await();
    System.out.println("********************" + increase.getRentIncrease());

  }

}
