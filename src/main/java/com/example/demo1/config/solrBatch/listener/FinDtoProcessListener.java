package com.example.demo1.config.solrBatch.listener;

import com.example.demo1.entity.FinDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * FinDtoProcessListener$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Component
@Slf4j
public class FinDtoProcessListener implements ItemProcessListener<FinDto, FinDto> {

  @Override
  public void beforeProcess(FinDto item) {

    //log.info("处理数据前");
  }

  @Override
  public void afterProcess(FinDto item, FinDto result) {
    //log.info("处理数据后");
  }

  @Override
  public void onProcessError(FinDto item, Exception e) {
    log.info("处理数据异常");
  }
}