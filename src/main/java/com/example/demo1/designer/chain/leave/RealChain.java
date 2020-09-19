package com.example.demo1.designer.chain.leave;

import java.util.List;

/**
 * RealChain$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public class RealChain implements Chain {


  public Request request;
  public List<Ratify> ratifyList;
  public int index;


  public RealChain(List<Ratify> ratifyList, Request request, int index) {
    this.ratifyList = ratifyList;
    this.request = request;
    this.index = index;
  }

  @Override
  public Request request() {
    return request;
  }

  @Override
  public Result proceed(Request request) {
    Result proceed = null;
    if (ratifyList.size() > index) {
      RealChain realChain = new RealChain(ratifyList, request, index + 1);
      Ratify ratify = ratifyList.get(index);
      proceed = ratify.deal(realChain);
    }

    return proceed;
  }
}