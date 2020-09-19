package com.example.demo1.config.esBatch.listener;

import com.example.demo1.entity.FinDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * FinDtoReadListener$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Slf4j
@Component
public class FinDtoEsReadListener implements ItemReadListener<FinDto> {


  @Override
  public void beforeRead() {
    //log.info("读数据前");
  }

  @Override
  public void afterRead(FinDto item) {

    //log.info("读数据后");
  }

  @Override
  public void onReadError(Exception ex) {
    //log.info("读数据异常");
  }
}