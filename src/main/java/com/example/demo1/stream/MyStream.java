package com.example.demo1.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * MyStream$
 *
 * @author shuai
 * @date 2020/5/14$
 */
@Slf4j
public class MyStream {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    list.add(7);
    list.add(8);
    int result = list.stream().filter(i->i!=0).sorted(Comparator.comparing(Integer::intValue))
        .mapToInt(Integer::intValue)
        .sum();
    log.info(String.valueOf(result));

    int result1 = list.stream()
        .map(i -> i * i).
            mapToInt(Integer::intValue)
        .sum();
    log.info(String.valueOf(result1));
  }

}