package com.example.demo1.study.concurrent.chapter10;

/*
BootStrapCLassLoader 是根类加载器 是C++编写的  加载虚拟机核心类库，java.lang是跟加载器加载的
-Xbootclasspath设置路径  获取不到根加载器的引用

 */
public class BootStrapClassLoader {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getContextClassLoader());
    System.out.println(String.class.getClassLoader());
    System.out.println(System.getProperty("sun.boot.class.path"));
  }

}
