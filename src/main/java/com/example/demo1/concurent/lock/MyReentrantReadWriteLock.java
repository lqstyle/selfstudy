package com.example.demo1.concurent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import lombok.extern.slf4j.Slf4j;

/*
判断 干活  通知
 */
@Slf4j
public class MyReentrantReadWriteLock {

  static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

  public static void main(String[] args) {

    ReadLock readLock = reentrantReadWriteLock.readLock();

    WriteLock writeLock = reentrantReadWriteLock.writeLock();

    Condition produceCondition = writeLock.newCondition();
    Condition consumeCondition = writeLock.newCondition();

    Book book = new Book();

    for (int j = 0; j <2 ; j++) {
      new Thread(() -> {
        writeLock.lock();
        try {
          for (int i = 0; i < 10; i++) {
            book.produceBook();
            consumeCondition.signal();
            System.out.println("生产线程等待");
            produceCondition.await();
            System.out.println("生产线程启动");
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          writeLock.unlock();
        }
      }).start();
    }




 /*   for (int i = 0; i < 10; i++) {
      new Thread(()->{
        readLock.lock();
        try {
          book.readBook();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          readLock.unlock();
        }
      }).start();
    }*/

    for (int j = 0; j <2 ; j++) {
      new Thread(() -> {
        writeLock.lock();
        try {
          for (int i = 0; i < 10; i++) {
            book.consumeBook();
            produceCondition.signal();
            System.out.println("消费线程等待");
            consumeCondition.await();
            System.out.println("消费线程启动");
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          writeLock.unlock();
        }
      }).start();
    }


  }

}

@Slf4j
class Book {

  private int i = 0;

  public void produceBook() {
    i++;
    log.info("方法名：produceBook,当前生产的书为第{},线程为：{}", i, Thread.currentThread());
  }

  public void consumeBook() {
    i--;
    log.info("方法名：produceBook,当前消费的书为第{}本,线程为：{}", i, Thread.currentThread());
  }


  public void readBook() {
    log.info("方法名：当前书总量：{}", i, Thread.currentThread());
  }
}
