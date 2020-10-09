package com.example.demo1.study.concurrent.chapter04;

import java.util.concurrent.atomic.AtomicInteger;

class Number {

  private AtomicInteger index = new AtomicInteger(0);
  private int max = 500;

  public /*synchronized*/ void increase() {
    while (index.get() < max) {
      index.incrementAndGet();
      System.out.println("当前号码为" + index);
    }
  }
}

public class CallNumber {

  public static void main(String[] args) {
    Number number = new Number();
    new Thread(() -> {
      number.increase();
    }).start();

    new Thread(() -> {
      number.increase();
    }).start();

    new Thread(() -> {
      number.increase();
    }).start();

    new Thread(() -> {
      number.increase();
    }).start();
  }
}
