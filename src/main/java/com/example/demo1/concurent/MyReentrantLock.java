package com.example.demo1.concurent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * MyReentrantLock$
 *
 * @author shuai
 * @date 2020/5/14$
 */
@Slf4j
public class MyReentrantLock {


  public static void main(String[] args) {

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition conditionOne = reentrantLock.newCondition();
    Condition conditionTwo = reentrantLock.newCondition();
    Runner runner = new Runner(100, conditionOne, conditionTwo, reentrantLock);

    new Thread(() -> {
      while (runner.getNumber() > 0) {
        try {
          runner.descNumber();
        } catch (InterruptedException e) {
          log.error(e.getMessage());
        }
      }
    }
    ).start();

    new Thread(() -> {
      while (runner.getNumber() > 0) {
        try {
          runner.descNumberTwo();
        } catch (InterruptedException e) {
          log.error(e.getMessage());
        }
      }
    }).start();

  }


}

@Slf4j
class Runner {

  private Integer number;

  private Condition conditionOne;

  private Condition conditionTwo;

  ReentrantLock reentrantLock;

  private boolean status = Boolean.TRUE;

  public Runner(Integer number, Condition conditionOne,
      Condition conditionTwo, ReentrantLock reentrantLock) {
    this.number = number;
    this.conditionOne = conditionOne;
    this.conditionTwo = conditionTwo;
    this.reentrantLock = reentrantLock;
  }

  public Integer getNumber() {
    return number;
  }

  public void descNumber() throws InterruptedException {

    reentrantLock.lock();
    try {
      while (status)
        conditionOne.await();


      if (number > 0) {
        log.info(Thread.currentThread().getName() + "--->" + number);
        number--;
      }

      status = Boolean.TRUE;
      conditionTwo.signal();
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      reentrantLock.unlock();
    }

  }


  public void descNumberTwo() throws InterruptedException {

    reentrantLock.lock();
    try {
      while (!status)
        conditionTwo.await();


      if (number > 0) {
        log.info(Thread.currentThread().getName() + "--->" + number);
        number--;
      }
      status = Boolean.FALSE;
      conditionOne.signal();
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      reentrantLock.unlock();
    }

  }
}