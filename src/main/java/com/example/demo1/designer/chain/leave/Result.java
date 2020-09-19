package com.example.demo1.designer.chain.leave;

/**
 * Result$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public class Result {

  public boolean isRatify;

  public String info;

  public Result() {

  }

  public Result(boolean isRatify, String info) {
    super();
    this.isRatify = isRatify;
    this.info = info;
  }

  public boolean isRatify() {
    return isRatify;
  }

  public void setRatify(boolean isRatify) {
    this.isRatify = isRatify;
  }

  public String getReason() {
    return info;
  }

  public void setReason(String info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return "Result [isRatify=" + isRatify + ", info=" + info + "]";
  }

}