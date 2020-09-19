package com.example.demo1.designer.chain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * DepartMentHandler$
 *
 * @author shuai
 * @date 2020/5/11$
 */
@Slf4j
public class DepartMentHandler extends Handler {

  @Override
  public String handlerRequest(String user, Double money) {
    if (null != money && money <= 1000) {
      if (StringUtils.equals(user, "lq")) {
        log.info("{}申请的费用{}成功", user, money);
      } else {
        log.info("{}申请的费用{}失败", user, money);
      }
    } else {
      if (null != getHandler()) {
        getHandler().handlerRequest(user, money);
      }
    }
    return null;
  }
}