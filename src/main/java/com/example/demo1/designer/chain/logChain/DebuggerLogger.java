package com.example.demo1.designer.chain.logChain;

/**
 * DebuggerLogger$
 *
 * @author shuai
 * @date 2020/5/21$
 */
public class DebuggerLogger extends MyLoggerChain {

  public DebuggerLogger(int level) {
    this.level = level;
  }

  @Override
  void logout(String message) {
    System.out.println("我打印的日志级别是debugger{}" + message);
  }
}