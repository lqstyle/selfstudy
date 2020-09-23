package com.example.demo1.lamada;

public class Two implements Three {

  public void myTwo(Three three, String s, Integer i) {
    System.out.println(three.three() + "" + s + i.toString());
  }

  @Override
  public String three() {
    return "";
  }
}
