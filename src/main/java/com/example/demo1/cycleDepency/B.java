package com.example.demo1.cycleDepency;

import java.lang.annotation.Annotation;
import javax.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liangqing
 * @version 1.0.0
 * @ClassName B.java
 * @Description TODO
 * @createTime 2020年08月04日 17:09:00
 */
@Component
public class B {
  @Resource
  private A a;

}