package com.example.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1.entity.TestPrice;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
public interface TestPriceService extends IService<TestPrice> {

  Logger log = LoggerFactory.getLogger(TestPriceService.class);

  void generatePriceCsv(String path, String fileName, Integer k);

  void loadPriceTest();

  List<TestPrice> getAll();

  void generateCsvs(String path, String fileName, List<TestPrice> object);

   void saveTestPrice(TestPrice testPrice);

  default void spendTime(String path, String fileName, List<TestPrice> object) {
    long start = System.currentTimeMillis();
    generateCsvs(path, fileName, object);
    long end = System.currentTimeMillis();
    log.info("生成文件花费的时间为：" + (end - start)/1000);
  }
}
