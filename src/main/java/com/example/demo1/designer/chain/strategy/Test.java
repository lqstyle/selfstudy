package com.example.demo1.designer.chain.strategy;

import javax.annotation.Resource;

/**
 * Test$
 *
 * @author shuai
 * @date 2020/5/21$
 */
public class Test {


  private static CalcContext calcContext = new CalcContext(new AddStrategy());

  public static void main(String[] args) {
    calc();
  }

  private static void calc() {
    calcContext.executeCalc();
  }

}