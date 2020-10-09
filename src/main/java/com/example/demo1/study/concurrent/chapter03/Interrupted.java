package com.example.demo1.study.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

public class Interrupted {

  public static void main(String[] args) {
    //Thread.interrupted 静态方法判断当前线程是否是可中断的
    System.out.println("当前线程的中断状态为"+Thread.interrupted());
    //中断当前线程
    Thread.currentThread().interrupt();
    //判断当前线程的中断状态
    System.out.println("当前线程的中断状态为"+Thread.currentThread().isInterrupted());
    //Thread.interrupted() 会擦除当前线程的可中断状态，底层调用的本地方法isInterrupted方法，参数为false
    System.out.println("当前线程的中断状态为"+Thread.interrupted());

    try {
      //执行当前线程的可中断方法
      TimeUnit.MINUTES.sleep(1);
    } catch (InterruptedException e) {
      System.out.println("我仍然是可中断的");
      e.printStackTrace();
    }

  }

}
