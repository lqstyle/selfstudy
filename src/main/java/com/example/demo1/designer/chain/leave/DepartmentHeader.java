package com.example.demo1.designer.chain.leave;

/**
 * DepartmentHeader$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public class DepartmentHeader implements Ratify {

  @Override
  public Result deal(Chain chain) {
    Request request = chain.request();
    System.out.println("DepartmentHeader=====>request:"
        + request.toString());
    if (request.days() > 7) {
      return new Result(false, "你这个完全没必要");
    }
    return new Result(true, "DepartmentHeader：不要着急，把事情处理完再回来！");
  }
}