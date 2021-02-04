package com.example.demo1.designer.chain.cons.adaptor;

/**
 * @author liangqing
 * @since 2021/1/26 9:24
 */
public class Adapter extends Source implements Targetable  {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }

}
