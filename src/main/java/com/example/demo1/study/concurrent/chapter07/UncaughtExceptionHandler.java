package com.example.demo1.study.concurrent.chapter07;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class UncaughtExceptionHandler {

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e)->{
      System.out.println(t.getName());
      System.out.println(ExceptionUtils.getStackTrace(e));
    });
    new Thread(()->{
        System.out.println(1/0);
    }).start();

  }
}
