package com.example.demo1.study.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liangqing
 * @since 2020/12/24 10:16
 */

class One implements Runnable {
    private Thread thread;

    public One(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("我是线程1");
        LockSupport.unpark(thread);
    }
}

public class MyLockSupport {

    private static Thread thread2 = null;
    private static Thread thread3 = null;


    private static final Lock lock = new ReentrantLock();
    private static final Condition conditionOne = lock.newCondition();
    private static final Condition conditionTwo = lock.newCondition();
    private static final Condition conditionThree = lock.newCondition();

    private static volatile Boolean one = Boolean.FALSE;
    private static volatile Boolean two = Boolean.FALSE;
    private static volatile Boolean three = Boolean.FALSE;

    public static void main(String[] args) throws InterruptedException {

        //第一种方式 通过 unsafe类的park和unpark（指定唤醒线程）
//        park();
        //第二种方式，join方式
//        join();
        //第三种方式 通过EntrantLock的 condition实现
        condition();
    }

    private static void condition() {

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("我是线程1");
                while(!two){

                }
                conditionTwo.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                two = true;
                conditionTwo.await();
                System.out.println("我是线程2");
                while(!three){

                }
                conditionThree.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                three = true;
                conditionThree.await();
                System.out.println("我是线程3");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

    private static void park() {
        thread3 = null;
        Thread thread1 = new Thread(() -> {
            System.out.println("我是线程1");
            LockSupport.unpark(thread2);
        });
        thread2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("我是线程2");
            LockSupport.unpark(thread3);
        });
        thread3 = new Thread(() -> {
            LockSupport.park();
            System.out.println("我是线程3");
            LockSupport.unpark(thread1);
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void join() throws InterruptedException {
        thread3 = null;
        Thread thread1 = new Thread(() -> {
            System.out.println("我是线程1");
        });
        thread2 = new Thread(() -> {
            System.out.println("我是线程2");
        });
        thread3 = new Thread(() -> {
            System.out.println("我是线程3");
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
