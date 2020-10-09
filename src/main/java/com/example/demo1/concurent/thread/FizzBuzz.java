package com.example.demo1.concurent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class FizzBuzz {

  private static int n = 15;

  private static int i = 1;

  private static volatile boolean four = false;

  private static volatile boolean three = false;

  private static volatile boolean two = false;

  private static volatile boolean one = true;

  // printFizz.run() outputs "fizz".
  public static void fizz() throws InterruptedException {
    while (i <= n) {
      while (!two) {

      }
      if (i > n) {
        break;
      }
      System.out.println("fizz");
      two = false;
      i++;
      one = true;
    }

  }

  // printBuzz.run() outputs "buzz".
  public static void buzz() throws InterruptedException {
    while (i <= n) {
      while (!three) {

      }
      if (i > n) {
        break;
      }
      System.out.println("buzz");
      three = false;
      i++;
      one = true;
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public static void fizzbuzz() throws InterruptedException {
    while (i <= n) {
      while (!four) {

      }
      if (i > n) {
        break;
      }
      System.out.println("fizzbuzz");
      four = false;
      i++;
      one = true;

    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public static void number() throws InterruptedException {
    while (i <= n) {
      while (!one) {

      }
      if (i > n) {
        break;
      }
      if (i % 3 != 0 && i % 5 != 0 && i % 15 != 0) {
        System.out.println(i);
        i++;
      } else {
        one = false;
        if (i % 3 == 0 && i % 15 != 0) {
          two = true;
        }
        if (i % 5 == 0 && i % 15 != 0) {
          three = true;
        }
        if (i % 15 == 0) {
          four = true;
        }
      }


    }
  }

  public static void main(String[] args) throws Exception {

    ExecutorService executorService = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new CallerRunsPolicy());

    executorService.execute(() -> {
      try {
        fizz();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executorService.execute(() -> {
      try {
        buzz();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executorService.execute(() -> {
      try {
        fizzbuzz();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executorService.execute(() -> {
      try {
        number();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
