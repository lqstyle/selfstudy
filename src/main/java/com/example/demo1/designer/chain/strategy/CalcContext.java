package com.example.demo1.designer.chain.strategy;

/**
 * CalcContext$
 *
 * @author shuai
 * @date 2020/5/21$
 */
public class CalcContext {

  private CalcStrategy calcStrategy;


  public CalcContext(CalcStrategy calcStrategy) {
    this.calcStrategy = calcStrategy;
  }

  Integer executeCalc() {
    return calcStrategy.calcData(100);
  }

}