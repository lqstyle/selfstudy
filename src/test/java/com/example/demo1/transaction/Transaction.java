package com.example.demo1.transaction;

import com.example.demo1.entity.TestBatch;
import com.example.demo1.service.TestBatchService;
import com.example.demo1.service.TestPriceService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Transaction {

  @Resource
  private TestBatchService testBatchService;
  @Resource
  private TestPriceService testPriceService;


  @Test
  public void test() {

    TestBatch testBatch = new TestBatch();
    testBatch.setCityName("lq");
    testBatch.setBalanceType("22");
    testBatchService.saveBatch(testBatch);

 /*   TestPrice testPrice = TestPrice.builder().type("www").money(BigDecimal.ONE).comid(123L)
        .price(BigDecimal.TEN).build();
    testPriceService.saveTestPrice(testPrice);
*/
  }

  @Test
  public void test111() {
    testBatchService.dityIsolation();
  }

  @Test
  public void test22() {
    testBatchService.noReaptableIsolation();
  }

}
