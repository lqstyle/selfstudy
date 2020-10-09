package com.example.demo1.study.concurrent.chapter04;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/*
if 会产生虚假唤醒，所以判断中用while 而不是for
 */
@Slf4j
public class MulitiEventQueue {

  //定义队列的最大为10
  private int max = 0;

  static class Event {

    private int x = 0;

    public Event(int x) {
      this.x = x;
    }
  }

  //存放event的队列
  private final List<Event> events = new LinkedList<>();

  public MulitiEventQueue(int max) {
    this.max = max;
  }

  //从队列中取元素，如果队列为空，则阻塞
  private void take() {
    synchronized (events) {
      while (events.size() == 0) {
        log.info("当前队列为空！{}", Thread.currentThread().getName());
        try {
          events.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      Event remove = events.remove(0);
      System.out.println("事件被消费" + remove.x);
      events.notifyAll();

    }

  }

  //往队列中添加元素，如果队列满了，则阻塞
  private void offer(Event event) {
    synchronized (events) {
      while (events.size() >= max) {
        log.info("当前队列已经满了！{}", Thread.currentThread().getName());
        try {
          events.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      events.add(event);
      System.out.println("当前事件生产成功" + event.x + "大小为" + events.size());
      events.notifyAll();

    }
  }

  public static void main(String[] args) {
    MulitiEventQueue MulitiEventQueue = new MulitiEventQueue(10);

    for (int j = 0; j < 10; j++) {
      new Thread(() -> {
        for (int i = 0; i < 100; i++) {
          try {
            MulitiEventQueue.take();
            TimeUnit.MILLISECONDS.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }, "Producer").start();
    }

    for (int j = 0; j < 10; j++) {

      new Thread(() -> {
        for (int i = 0; i < 100; i++) {
          MulitiEventQueue.offer(new MulitiEventQueue.Event(i));
        }
      }, "Consumer").start();
    }

  }

}
