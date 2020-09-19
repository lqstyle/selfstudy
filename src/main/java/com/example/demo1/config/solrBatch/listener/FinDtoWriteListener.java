package com.example.demo1.config.solrBatch.listener;

import com.example.demo1.entity.FinDto;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

/**
 * MessageWriteListener$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Component
@Slf4j
public class FinDtoWriteListener implements ItemWriteListener<FinDto> {

  @Override
  public void beforeWrite(List<? extends FinDto> items) {
    List<FinDto> newList = Lists.newLinkedList(items);
    newList.removeIf(item -> StringUtils.equals(item.getOrdercode(), "ZNLHZL-2019-27681"));
    log.info("写入数据前");
  }

  @Override
  public void afterWrite(List<? extends FinDto> items) {
    log.info("写入数据后");
  }

  @Override
  public void onWriteError(Exception exception, List<? extends FinDto> items) {

    log.info(String.format("%s%n", exception.getMessage()));

    for (FinDto finDto : items) {
      log.info(String.format("Failed writing message id: %s", finDto.getOrdercode()));
    }
  }
}