package com.example.demo1.study.concurrent.chapter04;

import java.util.HashMap;
import java.util.Map;

public class HashMapDeadLock {

  private final Map<String, String> MAP = new HashMap<>();


  private void add(String key, String value) {
    MAP.put(key, value);
    System.out.println("当前线程为"+Thread.currentThread());
  }


  public static void main(String[] args) {
    HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
    for (int i = 0; i < 2; i++) {
      new Thread(() -> {
        for (int j = 0; j < Integer.MAX_VALUE; j++) {
          hashMapDeadLock.add(String.valueOf(j), String.valueOf(j));
        }
      }, String.valueOf(i)).start();
    }

  }
}
