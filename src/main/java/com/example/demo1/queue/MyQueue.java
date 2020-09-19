package com.example.demo1.queue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * MyQueue$
 *
 * @author shuai
 * @date 2020/5/18$
 */
@Slf4j
public class MyQueue implements Serializable {

  public static void main(String[] args) {
    Queue<String> queue = new LinkedList<>();
    queue.offer("1");
    queue.offer("2");
    queue.offer("3");
    queue.offer("4");
    queue.offer("5");
    queue.offer("6");

    queue.forEach(s ->
      log.info(s)
    );
    queue.poll();
    queue.forEach(s ->
        log.info(s)
    );
    queue.element();
    queue.forEach(s ->
        log.info(s)
    );

    queue.peek();
    queue.forEach(s ->
        log.info(s)
    );

    AtomicReference<Queue>  atomicReference = new AtomicReference<>();
    atomicReference.set(queue);
    Queue<String> targetQueue = new LinkedList<>();
    queue.forEach(q->{
      String s= StringUtils.EMPTY;
      BeanUtils.copyProperties(q,s);
      targetQueue.add(s);
    });

    atomicReference.compareAndSet(queue, targetQueue);
    log.info(atomicReference.get().toString());

  }

}