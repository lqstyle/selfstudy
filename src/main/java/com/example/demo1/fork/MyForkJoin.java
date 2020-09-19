package com.example.demo1.fork;

import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ForkJoin$
 *
 * @author shuai
 * @date 2020/4/3$
 */
@Data
@AllArgsConstructor
public class MyForkJoin extends RecursiveTask<Long> {

  private long start;

  private long end;


  public static final long THREAD_HOLD = 10000L;

  @Override

  protected Long compute() {
    if (end - start <= THREAD_HOLD) {
      long sum = 0;
      for (long i = start; i < end; i++) {
        sum += i;
      }
      return sum;
    } else {
      long middle = (end - start) / 2;
      MyForkJoin left = new MyForkJoin(start, middle);
      left.fork();
      MyForkJoin right = new MyForkJoin(middle, end);
      right.fork();
      return left.join() + right.join();
    }
  }
}