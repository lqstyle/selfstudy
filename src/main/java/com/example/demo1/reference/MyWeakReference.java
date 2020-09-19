package com.example.demo1.reference;

import java.lang.ref.WeakReference;

/**
 * @author liangqing
 * @version 1.0.0
 * @ClassName Reference.java
 * @Description  弱引用，主要用于解决耦合对象之间的关系，方便回收，
 *
 * 可以用来跟踪对象呗垃圾回收的活动。
 * 一般可以通过虚引用达到回收一些非java内的一些资源比如堆外内存的行为。
 * 例如：在 DirectByteBuffer 中，会创建一个 PhantomReference 的子类
 * Cleaner的虚引用实例用来引用该 DirectByteBuffer 实例，Cleaner
 * 创建时会添加一个 Runnable 实例，当被引用的 DirectByteBuffer 对象不可达被垃圾回收时，
 * 将会执行 Cleaner 实例内部的 Runnable 实例的 run 方法，用来回收堆外资源
 *
 *
 *
 *
 * @createTime 2020年07月01日 09:35:00
 */
public class MyWeakReference {

  public static void main(String[] args) {
    A a = new A();
    B b = new B(a);
    a=null;
    System.gc();
   // System.out.println(b.getWeakReference());
    System.out.println(b.getA());
  }

}

class B<T> {
/*
  private WeakReference<T> weakReference;


  public B(T t) {
    weakReference = new WeakReference<>(t);
  }

  public T getWeakReference() {
    return weakReference.get();
  }*/

private A a;

  public B(A a) {
    this.a = a;
  }

  public A getA() {
    return a;
  }
}

class A {

  public A() {
  }
}