package com.example.demo1.concurent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FizzLock {

  private static int n = 15;

  private static int i = 1;

  private static ReentrantLock reentrantLock = new ReentrantLock();

  private static Condition oneCondition = reentrantLock.newCondition();

  private static Condition twoCondition = reentrantLock.newCondition();

  private static Condition threeCondition = reentrantLock.newCondition();

  private static Condition fourCondition = reentrantLock.newCondition();


  // printFizz.run() outputs "fizz".
  public static void fizz() throws InterruptedException {
    reentrantLock.lock();
    try {
      log.info("fizz 当前线程{}",Thread.currentThread().getName());
      while (i <= n) {
        if (i > n) {
          break;
        }
        if (i % 3 == 0 && i % 15 != 0) {
          System.out.println("fizz");
          i++;
        }
        oneCondition.signal();
        twoCondition.await();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }

  }

  // printBuzz.run() outputs "buzz".
  public static void buzz() throws InterruptedException {
    reentrantLock.lock();
    try {
      log.info("buzz 当前线程{}",Thread.currentThread().getName());
      while (i <= n) {
        if (i > n) {
          break;
        }
        if (i % 5 == 0 && i % 15 != 0) {
          System.out.println("buzz");
          i++;
        }
        oneCondition.signal();
        threeCondition.await();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public static void fizzbuzz() throws InterruptedException {
    reentrantLock.lock();
    try {
      log.info("fizzbuzz 当前线程{}",Thread.currentThread().getName());
      while (i <= n) {
        if (i > n) {
          break;
        }

        if (i % 15 == 0) {
          System.out.println("fizzbuzz");
          i++;
        }
        oneCondition.signal();
        fourCondition.await();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public static void number() throws InterruptedException {
    reentrantLock.lock();
    try {
      log.info("number 当前线程{}",Thread.currentThread().getName());
      while (i <= n) {
        if (i > n) {
          break;
        }
        if (i % 3 != 0 && i % 5 != 0 && i % 15 != 0) {
          System.out.println(i);
          i++;
        } else {
          if (i % 3 == 0 && i % 15 != 0) {
            twoCondition.signal();
          }
          if (i % 5 == 0 && i % 15 != 0) {
            threeCondition.signal();
          }
          if (i % 15 == 0) {
            fourCondition.signal();
          }
          oneCondition.await();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
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
