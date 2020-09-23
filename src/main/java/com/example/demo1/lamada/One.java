package com.example.demo1.lamada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class One {

  public abstract String myTss();

  public static String myT(String name, String id) {
    return name.concat(id);
  }

  public static void main(String[] args) {

    new Two().myTwo(() -> myT("lq", "3"), "myTss()", 1);
    new Two().myTwo(() -> Four.eeee("lq22", "3222"), "myTss333()", 1);
    new Two().myTwo(() -> Four.eeese("2", "3"), "myTss333()", 1);

    List<String> ss = new ArrayList<>();
    ss.add("1");
    ss.add("2");
    ss.sort(Comparator.comparing(String::hashCode));
    new Two().myTwo(new Three() {
      @Override
      public String three() {
        return myT("lq", "3");
      }
    }, "myTss()", 1);


  }

}
