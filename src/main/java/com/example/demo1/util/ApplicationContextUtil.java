package com.example.demo1.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextUtil$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

  private ApplicationContextUtil() {

  }

  private static ApplicationContext context = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    if (null == context) {
      context = applicationContext;
    }
  }

  public static ApplicationContext getApplicationContext() {
    return context;
  }

  public static <T> T getBean(Class<T> clazz) {
    return getApplicationContext().getBean(clazz);
  }

  public static <T> T getBean(String name, Class<T> clazz) {
    return getApplicationContext().getBean(name, clazz);
  }

  public static Object getBean(String beanName) {
    return getApplicationContext().getBean(beanName);
  }

  public static final Object getBean(String beanName, String className)
      throws ClassNotFoundException {
    Class<?> clz = Class.forName(className);
    return getApplicationContext().getBean(beanName, clz.getClass());
  }


  public static boolean containsBean(String name) {
    return getApplicationContext().containsBean(name);
  }


  public static boolean isSingleton(String name) {
    return getApplicationContext().isSingleton(name);
  }


  public static Class<?> getType(String name) {
    return getApplicationContext().getType(name);
  }


  public static String[] getAliases(String name) {
    return getApplicationContext().getAliases(name);
  }

}