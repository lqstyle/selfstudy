package com.example.demo1.designer.chain.leave;

/**
 * GroupLeader$
 *
 * @author shuai
 * @date 2020/5/11$
 */
public class GroupLeader implements Ratify {

    @Override
    public Result deal(Chain chain) {
      Request request = chain.request();
      System.out.println("GroupLeader=====>request:" + request.toString());

      if (request.days() > 1) {
        // 包装新的Request对象
        Request newRequest = new Request.Builder().newRequest(request)
            .setManagerInfo(request.name() + "平时表现不错，而且现在项目也不忙")
            .build();
        return chain.proceed(newRequest);
      }

      return new Result(true, "GroupLeader：早去早回");
    }
}