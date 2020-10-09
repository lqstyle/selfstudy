package com.example.demo1.study.concurrent.chapter04;


import java.util.concurrent.TimeUnit;

class Dead {

  private final Object mutex = new Object();
  private final Object mutex1 = new Object();

  private int i = 0;
  private int j = 0;

  public void sell() {
    synchronized (mutex) {
      System.out.println("当前线程" + Thread.currentThread());
      i++;
      buy();
    }

  }


  public void buy() {
    synchronized (mutex1) {
      System.out.println("当前线程" + Thread.currentThread());
      j++;
      try {
        TimeUnit.MINUTES.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      sell();
    }

  }
}

public class DeadLock {

  public static void main(String[] args) {
    Dead dead = new Dead();
    new Thread(() -> {
      dead.sell();
    }).start();

    new Thread(() -> {
      dead.buy();
    }).start();
  }


}
