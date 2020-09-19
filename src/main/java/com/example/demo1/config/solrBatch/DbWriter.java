package com.example.demo1.config.solrBatch;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1.entity.FinDto;
import com.example.demo1.entity.TestBatch;
import com.example.demo1.mapper.TestBatchMapper;
import com.example.demo1.service.TestBatchService;
import com.example.demo1.util.CommonUtils;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * DbWriter$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Component
public class DbWriter implements ItemWriter<FinDto> {

  public static final Integer SPLIT_NUMBER = 1000;


  @Resource
  private TestBatchMapper testBatchMapper;

  @Resource
  private TestBatchService testBatchService;


  @Override
  public void write(List<? extends FinDto> items) throws Exception {

    List<String> orderCodes = items.parallelStream()
        .filter(item -> StringUtils.isNotBlank(item.getOrdercode())).map(FinDto::getOrdercode)
        .collect(
            Collectors.toList());

    //获取数据库所有存在的数据
    List<TestBatch> testBatchList = Lists.newLinkedList();
    if(!CollectionUtils.isEmpty(orderCodes)){
      List<List<String>> resultList = CommonUtils.groupList(orderCodes, SPLIT_NUMBER);
      resultList.parallelStream().forEach(result->{
        QueryWrapper<TestBatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(TestBatch.ORDERCODE,result);
        testBatchList.addAll(testBatchMapper.selectList(queryWrapper));
      });
    }
    //此处需要做转换，要不然用流操作会报错
    List<FinDto> convertItem = Lists.newArrayList();
    convertItem.addAll(items);

    if(!CollectionUtils.isEmpty(testBatchList)){
      convertItem.removeIf(item -> testBatchList.stream().anyMatch(
          testBatch -> StringUtils.equals(item.getOrdercode(), testBatch.getOrdercode())));
    }

    //组织FinDto 对象成 TestBatch
    List<TestBatch>  testBatchesDbs = convertItem.parallelStream()
        .map(item ->{
          TestBatch testBatch = new TestBatch();
          BeanUtils.copyProperties(item,testBatch);
          return testBatch;
        }).collect(Collectors.toList());

    if(!CollectionUtils.isEmpty(testBatchesDbs)){
      //入库,分批插入
      List<List<TestBatch>> resultList = CommonUtils.groupList(testBatchesDbs, SPLIT_NUMBER);
      resultList.parallelStream().forEach(testBatchs -> {
        testBatchService.saveBatch(testBatchs);
      });
    }
  }
}