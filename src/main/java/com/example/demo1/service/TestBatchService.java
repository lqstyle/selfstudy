package com.example.demo1.service;

import com.example.demo1.entity.TestBatch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1.entity.TestPrice;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-04-08
 */
public interface TestBatchService extends IService<TestBatch> {
  void loadTestBatch(String fileName);

  void saveBatch(TestBatch testBatch);

  void savePrice(TestPrice testPrice);

  //脏读 ，并发度最高
  void dityIsolation();

  //不可重复读
  void noReaptableIsolation();

  //幻读
  void hdIsolation();
}
