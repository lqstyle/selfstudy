package com.example.demo1.study.concurrent;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChapterOne {

  public static void main(String[] args) throws InterruptedException {
    Thread thread =new Thread(() -> {
      try {
        dancing();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.start();
/*    TimeUnit.SECONDS.sleep(2);
    thread.start();*/

    new Thread(() -> {
      try {
        getProcessors();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    listen();
  }

  private static void getProcessors() throws InterruptedException {
    log.info("当前操作系统的处理器为:{},线程为{}", Runtime.getRuntime().availableProcessors(),
        Thread.currentThread().getName() + " " + Thread.currentThread().getId());
    TimeUnit.SECONDS.sleep(1);
  }

  private static void dancing() throws InterruptedException {
    for (; ; ) {
      log.info("我正在跳舞{}", Thread.currentThread().getName() + " " + Thread.currentThread().getId());
      TimeUnit.SECONDS.sleep(1);
    }
  }

  private static void listen() throws InterruptedException {
    for (; ; ) {
      log.info("我正在听音乐{}", Thread.currentThread().getName() + " " + Thread.currentThread().getId());
      TimeUnit.SECONDS.sleep(1);
    }
  }

}
