package com.example.demo1.study.concurrent;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChapterTwo extends Thread {

  private final static AtomicInteger ATOMICINTEGER = new AtomicInteger(0);

  public static void main(String[] args) {
/*    Thread one = new Thread("one");
    ThreadGroup threadGroup = new ThreadGroup("test");
    Thread two = new Thread(threadGroup, "two");
    Thread thread = Thread.currentThread();
    log.info("main线程的线程组为{}", thread.getThreadGroup().getName());
    log.info("one 和 main是同一个线程组{}",
        one.getThreadGroup().getName() == thread.getThreadGroup().getName());
    log.info("two 和 main是同一个线程组{}",
        two.getThreadGroup().getName() == thread.getThreadGroup().getName());
    log.info("one 和 main不是同一个线程组{}",
        two.getThreadGroup().getName() != thread.getThreadGroup().getName());*/

    while (true) {
      Thread thread = new ChapterTwo();
      thread.start();
    }


  }


  @SneakyThrows
  @Override
  public void run() {
    System.out.println(ATOMICINTEGER.incrementAndGet());

    while (true)
      try {
/*        HashSet hashSet = new HashSet<String>();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.get(0);*/
        Thread.sleep(Integer.MAX_VALUE);
        TimeUnit.DAYS.sleep(1);
        TimeUnit.HOURS.sleep(1);
        TimeUnit.MINUTES.sleep(10);
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        break;
      }
  }

}
