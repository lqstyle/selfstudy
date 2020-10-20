package com.example.demo1.functionInterface;

import java.text.SimpleDateFormat;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionInterface {

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      new Thread(()->{
        threadLocal.set("123");
        threadLocal.get();
        System.out.println();
      }).start();
    }

    //函数
    IntFunction<Integer> function = (t) ->123;
    log.info("方法名：main{}", function.apply(123));
    //断言
    Predicate<String> predicate = "123"::equals;
    log.info("方法名：main,断言结果{}", predicate.test("123"));

    //生产方
    Supplier<String> supplier = () -> {
      return "123";
    };
    log.info("方法名：main,结果{}", supplier.get());

    //消费方
    Consumer<String> consumer = (t) ->
        log.info("方法名：main,结果{}", t.toCharArray());
    consumer.accept("123");

  }

}
