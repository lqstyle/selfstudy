package com.example.demo1.designer.chain.strategy;

import org.springframework.stereotype.Component;

/**
 * SubStrategy$
 *
 * @author shuai
 * @date 2020/5/21$
 */
@Component
public class SubStrategy implements  CalcStrategy {

  @Override
  public Integer calcData(Integer num) {
    return num-10;
  }
}