package com.example.demo1.designer.chain;

/**
 * HandlerTest$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public class HandlerTest {

  public static void main(String[] args) {
    Handler projectHandler = new ProjectHandler();
    Handler departHandler = new DepartMentHandler();
    Handler centerHandler = new CenterHandler();

    projectHandler.setHandler(departHandler);
    departHandler.setHandler(centerHandler);


    projectHandler.handlerRequest("lq",3000D);

  }
}