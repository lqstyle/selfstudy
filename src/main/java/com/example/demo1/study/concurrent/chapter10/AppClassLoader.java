package com.example.demo1.study.concurrent.chapter10;

/*
是自定义加载器的父加载器
是ExtClassLoader的子类加载器
 */
public class AppClassLoader {

  public static void main(String[] args) {
    System.out.println(
        System.getProperty("java.class.path")
    );

    System.out.println(AppClassLoader.class.getClassLoader());
  }

}
