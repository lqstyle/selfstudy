package com.example.demo1.study.concurrent.chapter05;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/*
生产者消费者
单线程间通讯
因为调用wait和notify必须有该对象的monitor，所以必须是同一对象

IllegalMonitorStateException
 */
@Slf4j
public class EventQueue {

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

  public EventQueue(int max) {
    this.max = max;
  }

  //从队列中取元素，如果队列为空，则阻塞
  private void take() {
    synchronized (events) {
      if (events.size() == 0) {
        log.info("当前队列为空！{}", Thread.currentThread().getName());
        try {
          events.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      Event remove = events.remove(0);
      System.out.println("事件被消费"+remove.x);
      events.notify();

    }

  }

  //往队列中添加元素，如果队列满了，则阻塞
  private void offer(Event event) {
    synchronized (events) {
      if (events.size() >= max) {
        log.info("当前队列已经满了！{}", Thread.currentThread().getName());
        try {
          events.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      events.add(event);
      System.out.println("当前事件生产成功" + event.x+"大小为"+events.size());
      events.notify();

    }
  }

  public static void main(String[] args) {
    EventQueue eventQueue = new EventQueue(10);

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          eventQueue.take();
          TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "Producer").start();

    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        eventQueue.offer(new EventQueue.Event(i));
      }
    }, "Consumer").start();

  }

}
