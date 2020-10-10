package com.example.demo1.concurent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;

/*
LockSupport 总结

park(Blocker)

因为对于Thread 的二进制字节流文件加载进来放到方法区中后，调用clint初始化生成Thread的Class对象
对于每一个线程实例，里面的parkBlocker Object类型的属性在不同的线程实例中对应的堆内存中的地址的偏移量是相同的
后续对于每一次针对线程的park（Blocker）设置parkBlocker的时候都是同一个偏移量,字段在类中的
偏移量是相同的个，方便进行监控
1.6提供的方法

底层是通过本地方法 park unpark（Unsafe 类 sun.misc下的）

硬件层面的实现在不同的操作系统下有不同的实现，因为主流的服务器都是linux操作系统
所以说下在linux操作系统下的实现

通过posix包下的pthread实现

park实现：
1.判断volatile修饰的 counter 是否>0 若大于0 则直接返回
2. 判断线程是否被中断了，若中断则直接返回
3. 判断时间是否小于0 或者在绝对时间下，时间是否<0 满足则返回
4. 阻塞前判断是否是被中断的或者申请互斥锁失败 pthread_mutex_trylock，中断则返回
5. 时间等于0 ，phread_cond_wait 则阻塞并设置阻塞时间phread_cond_timewait
6. 阻塞相应时间后，通过 pthread_mutex_unlock 进行唤醒，同时将 counter 设置为0

unpark 实现：
先通过pthread_mutex_lock 获取到当前是否获得锁
若>0 给counter加1
调用pthread_mutex_unlock 释放锁
 */
@Slf4j
public class LockSuppport {

  public static void main(String[] args) throws InterruptedException {

    Object o = new Object();
    Object o1 = new Object();
    Thread thread = new Thread(
        () -> {
          System.out.println("线程1 开始执行");
          LockSupport.park(o);
          log.info("线程1 被唤醒");
        });
    thread.start();

    Thread thread3 = new Thread(
        () -> {
          System.out.println("线程3 开始执行");
          LockSupport.park(o1);
          log.info("线程3 被唤醒");
        });
    thread3.start();

    TimeUnit.SECONDS.sleep(2);

    Thread thread2 = new Thread(() -> {
      System.out.println("线程2 开始执行");
      Object blocker = LockSupport.getBlocker(thread);

      LockSupport.unpark(thread);
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Object blocker1 = LockSupport.getBlocker(thread);

      System.out.println(blocker == blocker1);



      LockSupport.unpark(thread3);
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Object blocker3= LockSupport.getBlocker(thread3);

      System.out.println(blocker == blocker3);
      System.out.println(blocker1 == blocker3);
    });
    thread2.start();


  }

  private static void testLockSupport() throws InterruptedException {
    Object object = new Object();

    Thread thread = new Thread(() -> {
      System.out.println("新线程开始");
      LockSupport.park(object);
      System.out.println("新线程被唤醒");
    });
    thread.start();

    System.out.println("主线程");
    TimeUnit.SECONDS.sleep(10);
    thread.interrupt();
    //LockSupport.unpark(thread);
    TimeUnit.SECONDS.sleep(1);
    System.out.println("结束");
  }


}
