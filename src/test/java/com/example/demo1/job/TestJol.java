package com.example.demo1.job;

import com.example.demo1.entity.Jol;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * TestJol$
 *
 * @author shuai
 * @date 2020/5/20$
 */
@Slf4j
public class TestJol {

  private static Jol jol = new Jol() ;

  public static void main(String[] args) {

   /* log.info(String.valueOf(jol.hashCode()));
    log.info(Integer.toHexString(jol.hashCode()));*/
    log.info(ClassLayout.parseInstance(jol).toPrintable());
  }

}