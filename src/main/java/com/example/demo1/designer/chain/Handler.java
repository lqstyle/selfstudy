package com.example.demo1.designer.chain;

import lombok.NoArgsConstructor;

/**
 * Handler$
 *
 * @author shuai
 * @date 2020/5/11$
 */
@NoArgsConstructor
public abstract class Handler {

  private Handler handler = null;

  public Handler getHandler() {
    return handler;
  }

  public void setHandler(Handler handler) {
    this.handler = handler;
  }


  public abstract String handlerRequest(String user, Double money);


}