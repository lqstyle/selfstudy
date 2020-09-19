package com.example.demo1.designer.chain.logChain;

/**
 * MyLoggerChain$
 *
 * @author shuai
 * @date 2020/5/21$
 */
public abstract class MyLoggerChain {

  public static int INFO = 1;
  public static int DEBUGGER = 2;
  public static int ERROR = 3;


  protected int level = 3;

  private MyLoggerChain nextMyLoggerChain;

  public void setNextMyLoggerChain(MyLoggerChain nextMyLoggerChain) {
    this.nextMyLoggerChain = nextMyLoggerChain;
  }

  public void logMessage(int level, String message) {
    if (level <= 3) {
      logout(message);
    }

    if (nextMyLoggerChain != null) {
      nextMyLoggerChain.logMessage(level, message);
    }

  }


  abstract void logout(String message);


}