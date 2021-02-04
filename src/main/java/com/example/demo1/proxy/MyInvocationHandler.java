package com.example.demo1.proxy;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liangqing
 * @since 2020/11/25 16:56
 */
@Slf4j
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 禁止指令重排序，多线程情况下，不会在构造函数中溢出
     * 加了storeload指令
     * X86 禁止其重排序
     */
    private final JdkProxy jdkProxy;

    public MyInvocationHandler(JdkProxy jdkProxy) {
        this.jdkProxy = jdkProxy;
    }



    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(jdkProxy);
        MyJdkProxy newProxyInstance = (MyJdkProxy)Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), jdkProxy.getClass().getInterfaces(), invocationHandler);
        System.out.println(newProxyInstance.speak("231312"));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("在调用之前，我要干点啥呢？");

        System.out.println("Method:" + method);

        Object invoke = method.invoke(jdkProxy, args);
        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("在调用之后，我要干点啥呢？");

        return invoke;
    }
}
