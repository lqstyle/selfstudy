package com.example.demo1.config.IteamReaderDBDemo;

import com.example.demo1.entity.TestPrice;
import com.example.demo1.service.TestPriceService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName DbJdbcWriter
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 11:52
 * @VERSION 1.0
 */
@Component
public class DbJdbcWriter implements ItemWriter<TestPrice> {

  @Value("${file.path}")
  private String path;

  @Resource
  private TestPriceService testPriceService;

  @Override
  public void write(List<? extends TestPrice> items) throws Exception {
    long a = System.currentTimeMillis();
    testPriceService.spendTime(path, "testBatch.csv", (List<TestPrice>) items);
    long b = System.currentTimeMillis();
    System.out.println("生成文件耗时：" + (b - a) / 1000);
  }
}
