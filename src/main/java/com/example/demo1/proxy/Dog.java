package com.example.demo1.proxy;

/**
 * Dog$
 *
 * @author shuai
 * @date 2020/4/27$
 */
public class Dog {
  final public void run(String name) {
    System.out.println("狗"+name+"----run");
  }

  public void eat() {
    System.out.println("狗----eat");
  }
}