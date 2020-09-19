package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1.entity.TestBatch;
import com.example.demo1.entity.TestPrice;
import com.example.demo1.mapper.TestBatchMapper;
import com.example.demo1.service.TestBatchService;
import com.example.demo1.service.TestPriceService;
import com.example.demo1.util.ApplicationContextUtil;
import java.math.BigDecimal;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-04-08
 *
 * 1.方法内部调用，事务不生效，A->B 相当于把B的代码块移动到A方法里
 * 2.方法内部调用，可以使用applicationContext和AopCOntext两种方式获取代理bean
 *
 *
 */
@Service
@Slf4j
public class TestBatchServiceImpl extends ServiceImpl<TestBatchMapper, TestBatch> implements
    TestBatchService {


  @Resource
  private TestPriceService testPriceService;


  @Override
  public void loadTestBatch(String fileName) {
    super.baseMapper.loadTestBatch(fileName);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void saveBatch(TestBatch testBatch) {
    getBaseMapper().insert(testBatch);

    TestPrice testPrice = TestPrice.builder().type("www").money(BigDecimal.ONE).comid(123L)
        .price(BigDecimal.TEN).build();

      //1. 事务不生效，不管A方法还是B方法抛异常，若不捕获，数据均不入库，若捕获，则均入库
      //this.savePrice(testPrice);

      //2. 事务生效，此时使用代理对象
      ((TestBatchServiceImpl) ApplicationContextUtil.getBean("testBatchServiceImpl")).savePrice(testPrice);
/*
      ((TestBatchServiceImpl) AopContext.currentProxy()).savePrice(testPrice);
*/

    /*throw new RuntimeException();*/
  }

  @Override
  @Transactional(propagation = Propagation.MANDATORY)
  public void savePrice(TestPrice testPrice) {

    testPriceService.saveTestPrice(testPrice);

    throw new RuntimeException();
  }

  @Override
  public void isolation() {

  }


}
