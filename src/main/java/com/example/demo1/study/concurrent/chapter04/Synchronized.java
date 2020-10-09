package com.example.demo1.study.concurrent.chapter04;


import java.util.concurrent.TimeUnit;

class Ser {

  private static Object mutex = new Object();

  public void sleep() {
    synchronized (mutex) {
      try {
        System.out.println("当前获取锁的线程是{}"+Thread.currentThread());
        TimeUnit.MINUTES.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class Synchronized {

  public static void main(String[] args) {
    Ser ser = new Ser();
    new Thread(() -> {
      ser.sleep();
    }).start();
    new Thread(() -> {
      ser.sleep();
    }).start();;
    new Thread(() -> {
      ser.sleep();
    }).start();;
    new Thread(() -> {
      ser.sleep();
    }).start();;
    new Thread(() -> {
      ser.sleep();
    }).start();;
  }

}
