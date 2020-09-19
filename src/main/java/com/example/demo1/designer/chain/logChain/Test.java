package com.example.demo1.designer.chain.logChain;

/**
 * Test$
 *
 * @author shuai
 * @date 2020/5/21$
 */
public class Test {


  private static MyLoggerChain getLoggerChains() {
    MyLoggerChain myLoggerChain = new InfoLogger(MyLoggerChain.INFO);
    MyLoggerChain myLoggerChain1 = new DebuggerLogger(MyLoggerChain.DEBUGGER);
    MyLoggerChain myLoggerChain2 = new ErrorChain(MyLoggerChain.ERROR);
    myLoggerChain.setNextMyLoggerChain(myLoggerChain1);
    myLoggerChain1.setNextMyLoggerChain(myLoggerChain2);
    return myLoggerChain;
  }


  public static void main(String[] args) {

    MyLoggerChain myLoggerChain = getLoggerChains();
    myLoggerChain.logMessage(MyLoggerChain.INFO, "this is infomation");
  }

}