package com.example.demo1.commonpool;

/*
jdk 1.6  字符串常量池在永久代，
jdk1.7 移到堆中
string.intern 首次遇到原则
 */
public class MyString {

  public static void main(String[] args) {
    String s = new StringBuilder("java").toString();
    String ss = s.intern();
    System.out.println(s==ss);
  }
}
