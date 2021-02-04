package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1.entity.TestBatch;
import com.example.demo1.entity.TestPrice;
import com.example.demo1.mapper.TestBatchMapper;
import com.example.demo1.service.TestBatchService;
import com.example.demo1.service.TestPriceService;
import com.example.demo1.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-04-08
 * <p>
 * 1.方法内部调用，事务不生效，A->B 相当于把B的代码块移动到A方法里 2.方法内部调用，可以使用applicationContext和AopCOntext两种方式获取代理bean
 */
@Service
@DependsOn("applicationContextUtil")
@Slf4j
public class TestBatchServiceImpl extends ServiceImpl<TestBatchMapper, TestBatch> implements
        TestBatchService {

    private TestBatchServiceImpl object;

    @PostConstruct
    private void initMethod() {
        object = ((TestBatchServiceImpl) ApplicationContextUtil.getBean("testBatchServiceImpl"));
    }

    private volatile Boolean flag = Boolean.FALSE;
    private volatile Boolean queryFlag = Boolean.FALSE;

    ExecutorService executorService = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10),
            Executors.defaultThreadFactory(), new CallerRunsPolicy());

    @Resource
    private TestPriceService testPriceService;


    @Override
    public void loadTestBatch(String fileName) {
        super.baseMapper.loadTestBatch(fileName);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBatch(TestBatch testBatch) {
        getBaseMapper().insert(testBatch);

        TestPrice testPrice = TestPrice.builder().type("www").money(BigDecimal.ONE).comid(123L)
                .price(BigDecimal.TEN).build();

        //1. 事务不生效，不管A方法还是B方法抛异常，若不捕获，数据均不入库，若捕获，则均入库
        //this.savePrice(testPrice);
      super.baseMapper.insert(testBatch);
        //2. 事务生效，此时使用代理对象
        ((TestBatchServiceImpl) ApplicationContextUtil.getBean("testBatchServiceImpl"))
                .savePrice(testPrice);


/*
      ((TestBatchServiceImpl) AopContext.currentProxy()).savePrice(testPrice);
*/

        /*throw new RuntimeException();*/
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void savePrice(TestPrice testPrice) {

        try {
            testPriceService.saveTestPrice(testPrice);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

//        throw new RuntimeException();

    }

    @Override
    @Transactional
    public void dityIsolation() {
        object.dityRead();
    }

    @Override
    public void noReaptableIsolation() {
        object.noReaptable();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void noReaptable() {
        //线程一执行新增
        executorService.execute(() -> {
            object.saveNoRepeatableMethod();
        });

        //线程二执行两次查询
        executorService.execute(() -> {
            object.queryTwoMethod();
        });
    }

    @Transactional
    public void saveNoRepeatableMethod() {
        while (!queryFlag) {

        }
        System.out.println("执行数据插入");
        TestBatch testBatch = new TestBatch();
        testBatch.setCityName("lq");
        testBatch.setBalanceType("22");
        getBaseMapper().insert(testBatch);

        flag = Boolean.TRUE;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void queryTwoMethod() {
        System.out.println("数据入库前读取");
        QueryWrapper<TestBatch> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("city_name", "lq");
        List<TestBatch> testBatches1 = getBaseMapper().selectList(queryWrapper1);
        if (!CollectionUtils.isEmpty(testBatches1)) {
            log.info("当前城市名{}", testBatches1.get(0).getCityName());
        }
        queryFlag = Boolean.TRUE;
        while (!flag) {

        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据入库后读取");
        QueryWrapper<TestBatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_name", "lq");
        List<TestBatch> testBatches = getBaseMapper().selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(testBatches)) {
            log.info("当前城市名{}", testBatches.get(0).getCityName());
        }
    }


    @Transactional
    public void dityRead() {
        //线程一执行新增
        executorService.execute(() -> {
            object.saveMethod();
        });

        //线程二执行查询
        executorService.execute(() -> {
            object.queryMethod();
        });
    }

    @Override
    public void hdIsolation() {

    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)  //出现脏读
    //@Transactional(isolation = Isolation.READ_COMMITTED)  //
    public void queryMethod() {
        System.out.println("线程查询");
        while (!flag) {

        }

        QueryWrapper<TestBatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_name", "lq");
        List<TestBatch> testBatches = getBaseMapper().selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(testBatches)) {
            log.info("当前城市名{}", testBatches.get(0).getCityName());
        }
    }


    @Transactional
    public void saveMethod() {
        System.out.println("执行数据插入");
        TestBatch testBatch = new TestBatch();
        testBatch.setCityName("lq");
        testBatch.setBalanceType("22");
        getBaseMapper().insert(testBatch);

        flag = Boolean.TRUE;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡眠苏醒");
        throw new RuntimeException();
    }


}
