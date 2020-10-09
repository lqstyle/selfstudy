package com.example.demo1.study.concurrent.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
注意，被阻塞的线程是执行join方法的线程，而不是调用join方法的线程
join
sleep
wait
都是可中断的

线程退出
jvm crash
异常捕获
volatile
join 可中断进行打断
Countdownlatch Semphore  CyclicBarrier
 */
public class Join {

  public static void main(String[] args) {

    //创建两个线程
    List<Thread> threads = IntStream.range(0, 2).mapToObj(Join::createThread)
        .collect(Collectors.toList());

    //启动两个线程
    threads.forEach(thread -> thread.start());

    //把两个线程join，阻塞当前执行join方法的线程，此时阻塞的是main线程，若把此处注释掉，则交替输出
    threads.forEach(thread ->
        {
          try {
            thread.join();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    );

    for (int i = 0; i < 5; i++) {
      System.out.println("当前线程" + Thread.currentThread().getName() + "  " + i);
      shortSleep();
    }

  }

  private static Thread createThread(int i) {
    return new Thread(() -> {
/*
//此方法废弃，可能不会释放monitor锁，会导致任务未执行完的问题
      Thread.currentThread().stop();
*/
      for (int j = 0; j < 5; j++) {
        System.out.println("当前线程" + Thread.currentThread().getName() + "  " + j);
        shortSleep();
      }

    }, String.valueOf(i));
  }

  private static void shortSleep() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
