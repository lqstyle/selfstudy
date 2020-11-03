package com.example.demo1;

import java.util.concurrent.atomic.AtomicInteger;
import sun.misc.Unsafe;

public class StringPool {


  public static final String s2 ="12liangqing";

  public static void main(String[] args) {

//    init();
   //first();

    ceils();
  }

  private static void ceils() {
    System.out.println(Math.ceil(3/2));
    System.out.println(Math.round(3/2));
  }

  private static void first() {
    Unsafe unsafe = Unsafe.getUnsafe();
    String s = new StringBuilder("12").append("liangqing").toString();
    String s1 = new StringBuilder("12").append("liangqing").toString();
    String ss = s.intern();
    AtomicInteger atomicInteger = new AtomicInteger(0);
    atomicInteger.compareAndSet(0,1);
    System.out.println(s1==ss);
  }

  private static void init() {
    String s = new StringBuilder("12").append("liangqing").toString();
    String ss = s.intern();

    System.out.println(s.hashCode());
    System.out.println(ss.hashCode());
    System.out.println(s == ss);
    System.out.println(s.equals(ss));

    String ee = new StringBuilder("ja").append("va").toString();
    String e = ee.intern();

    System.out.println(e == ee);
  }

}
