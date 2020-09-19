package com.example.demo1.solr;

import com.example.demo1.fork.MyForkJoin;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * ParallelTest$
 *
 * @author shuai
 * @date 2020/4/3$
 */
@Slf4j
public class ParallelTest {

  public static void main(String[] args) {
    Instant instant = Instant.now();
    long result = LongStream.rangeClosed(0, 1000000000L).parallel().reduce(0L, Long::sum);
    log.info(String.valueOf(result));
    Instant end = Instant.now();
    log.info("耗时：", (Duration.between(end, instant)));
  }

  @Test
  public void test() {
    Instant start = Instant.now();
    ForkJoinPool forkJoinPool = new ForkJoinPool();

    ForkJoinTask<Long> myForkJoin = new MyForkJoin(0,1000000000L);

    long sum = forkJoinPool.invoke(myForkJoin);
    //ForkJoinTask<Long> sum = forkJoinPool.submit(myForkJoin);
    log.info(String.valueOf(sum));
    Instant end = Instant.now();
    log.info("耗时", Duration.between(end, start));
  }

}