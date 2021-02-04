package com.example.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author liangqing
 * @since 2020/12/19 15:40
 *
 * jdk1.8 默认开启指针压缩
 * -XX:+UseCompressedOops
 * 关闭 用 -XX:-UseCompressedOops
 *
 * 对象头12字节 ，关闭压缩 16字节
 */
public class JolCheck {
    static Base base;
    public static void main(String[] args) throws InterruptedException {
        base = new Base();
        System.out.println("锁定前  "+ClassLayout.parseInstance(base).toPrintable());
        sync();
//        TimeUnit.SECONDS.sleep(20);
        System.out.println("锁定后  "+ClassLayout.parseInstance(base).toPrintable());
//        Base base1 = new Base();
//        System.out.println(ClassLayout.parseInstance(base1).toPrintable());
//        BigBase bigBase  =new BigBase();
//        bigBase.getBaseList().add(base);
//        bigBase.getBaseList().add(base1);
//        System.out.println(ClassLayout.parseInstance(bigBase).toPrintable());
//        System.out.println(GraphLayout.parseInstance(bigBase).toPrintable());
//        System.out.println(GraphLayout.parseInstance(bigBase).totalSize());
    }

    public static void sync() throws InterruptedException {
        synchronized (base){
            System.out.println("我也不知道要打印什么");
        }
    }

}
