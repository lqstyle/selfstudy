package com.example.demo1.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * MyMethodInterceptor$
 *
 * @author shuai
 * @date 2020/4/27$
 */
public class MyMethodInterceptor implements MethodInterceptor {

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("这里是对目标类进行增强！！！");
    //注意这里的方法调用，不是用反射哦！！！
    Object object = methodProxy.invokeSuper(o, objects);
    return object;
  }
}