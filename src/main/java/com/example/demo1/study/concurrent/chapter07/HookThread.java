package com.example.demo1.study.concurrent.chapter07;

public class HookThread {

  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new Thread(()->{
      System.out.println("钩子线程");
    }));

    Runtime.getRuntime().addShutdownHook(new Thread(()->{
      System.out.println("钩子线程");
    }));
    System.out.println("11111");
  }

}
