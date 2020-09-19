package com.example.demo1.concurent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MyThread$
 *
 * @author shuai
 * @date 2020/5/21$
 */
public class MyThread  {


  public static void main(String[] args) {
    MyRun myRun = new MyRun();

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i <10 ; i++) {
      executorService.submit(myRun);
    }
  }

}