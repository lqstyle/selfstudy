package com.example.demo1.concurent.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * MyRun$
 *
 * @author shuai
 * @date 2020/5/21$
 */
@Slf4j
public class MyRun implements Runnable {

  private final int SUM = 1000;
  private volatile int index = 1;



  @Override
  public void run() {
    while (index <= SUM) {
      log.info(String.valueOf(Thread.currentThread().getId()) + " 当前的号码是" + index++);
    }

  }
}