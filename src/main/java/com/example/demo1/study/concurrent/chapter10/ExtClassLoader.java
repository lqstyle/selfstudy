package com.example.demo1.study.concurrent.chapter10;

/*
父加载器是BootStrapClassLoader
子加载器是 AppClassLoader
 */
public class ExtClassLoader {

  public static void main(String[] args) {
    System.out.println(System.getProperty("java.ext.dirs"));
  }

}
