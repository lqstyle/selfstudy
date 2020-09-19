package com.example.demo1.designer.chain.leave;

/**
 * Chain$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public interface Chain {

  // 获取当前request
  Request request();

  // 转发request
  Result proceed(Request request);
}
