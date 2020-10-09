package com.example.demo1.study.concurrent.chapter03;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*

yield会提示cpu释放当前cpu的使用权，但是cpu并不会每次都对yield的提示生效，cpu资源不紧张会忽略这种提示
线程从RUNNABLE状态转换到RUNNING状态
 */
public class ChapterThree {

  public static void main(String[] args) throws InterruptedException {
    //IntStream.range(0, 2).mapToObj(ChapterThree::create).forEach(Thread::start);
    Thread thread = new Thread(()->{
      try {
        TimeUnit.MINUTES.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("我被打断了");
        e.printStackTrace();
      }
    });
    thread.start();
    TimeUnit.SECONDS.sleep(1);
    thread.interrupt();
    System.out.println(thread.getState());
  }

  private static Thread create(int i) {
    return new Thread(() -> {
    /*  if (i == 0) {
        Thread.yield();
      }*/
      System.out.println("当前线程是" + i);
    });
  }


  class ThreadYield {

  }

}
