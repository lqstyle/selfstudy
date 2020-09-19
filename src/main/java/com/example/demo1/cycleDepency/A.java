package com.example.demo1.cycleDepency;

import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author liangqing
 * @version 1.0.0
 * @ClassName A.java
 * @Description TODO
 * @createTime 2020年08月04日 17:09:00
 *
 * 1. 什么是循环依赖（ 循环依赖是A->B, B->A）
 * 2. 循环依赖什么情况下可以被处理（ 前置条件：1.循环依赖的bean必须是单例 2.依赖注入的方式不全是构造注入）
 * 3. spring 是如何解决循环依赖的
 * （
 * 1.简单的循环依赖
 * 2. 结合了AOP的循环依赖
 *
 * 原因解析：spring创建bean 默认是按照自然顺序创建的
 * AbstractAutowiteCapableBeanFactory
 * createBeanInstance 实例化bean new对象
 * populateBean       填充对象
 * initializeBean     初始化bean 完成AOP
 *
 *
 * getBean()->doGetBean()->getSingleTon(beanname)->getSingleTon(beanname,true)
 * singletonObjects       存储创建好的单例bean
 * earlySingletonObjects   完成实例化，但是没有属性注入和初始化
 * singletonFactories       提前暴露的单例工厂，二级缓存中存储的就是从这个工厂中获取的对象
 *
 * 先去缓存池中获取，如果获取到，则返回，获取不到，则进行创建
 * ）
 */
@Component
public class A {


}