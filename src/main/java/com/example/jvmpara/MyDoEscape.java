package com.example.jvmpara;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangqing
 * @since 2020/12/21 11:33
 */
public class MyDoEscape {

    private final static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {



        long b = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            i = alloc(i);
        }
        long c = System.currentTimeMillis();
        System.out.println((c-b));

        System.out.println("创建的对象数量"+count);
        TimeUnit.SECONDS.sleep(1000);
    }

    private static int alloc(int i) throws InterruptedException {
        synchronized (MyDoEscape.class){
            User user = new User(1,i);
            user.execute();
            System.out.println("对象大小"+user.toString().getBytes().length);
            count.incrementAndGet();
        }
        return i;
    }
}
