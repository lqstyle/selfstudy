package com.example.spring;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liangqing
 * @since 2020/12/19 9:16
 */
@Component
public class MyFactoryBean implements FactoryBean, ApplicationContextAware {


    private static ApplicationContext applicationContext;

    private String message ;


    public MyFactoryBean(String message) {
        this.message = message;
    }

    @Override
    public Object getObject() throws Exception {
        return new MyFactoryBean("我是 梁晴 工厂 ");
    }

    @Override
    public Class<?> getObjectType() {
        return MyFactoryBean.class;
    }

    public static void main(String[] args) {
        final MyFactoryBean myFactoryBean = (MyFactoryBean)applicationContext.getBean("myFactoryBean");
        final MyFactoryBean bean = (MyFactoryBean)applicationContext.getBean("&myFactoryBean");
        System.out.println(myFactoryBean == bean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }
}
