package com.example.demo1.designer.chain.leave;

/**
 * TestMain$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public class TestMain {

  public static void main(String[] args) {

    Request request = new Request.Builder().setName("张三").setDays(5)
        .setReason("事假").build();
    ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
    Result result = client.execute(request);

    System.out.println("结果：" + result.toString());
  }
}