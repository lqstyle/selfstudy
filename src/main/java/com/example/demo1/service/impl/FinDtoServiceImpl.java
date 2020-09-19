package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csvreader.CsvWriter;
import com.example.demo1.entity.FinDto;
import com.example.demo1.entity.TestBatch;
import com.example.demo1.mapper.FinDtoMapper;
import com.example.demo1.mapper.TestBatchMapper;
import com.example.demo1.service.FinDtoService;
import com.example.demo1.util.CommonUtils;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName FinDtoServiceImpl
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 17:59
 * @VERSION 1.0
 */
@Component
public class FinDtoServiceImpl extends ServiceImpl<FinDtoMapper, FinDto> implements FinDtoService {

  public static final Integer SPLIT_NUMBER = 1000;

  @Resource
  private TestBatchMapper testBatchMapper;


  @Override
  public void generateCsvs(String path, String fileName, List<FinDto> object) {

   /* List<String> orderCodes = object.parallelStream()
        .filter(item -> StringUtils.isNotBlank(item.getOrdercode())).map(FinDto::getOrdercode)
        .collect(
            Collectors.toList());

    //获取数据库所有存在的数据
    List<TestBatch> testBatchList = Lists.newLinkedList();
    //查询所有数据库存在的数据
    queryData(orderCodes, testBatchList);*/

    //不转换
    //List<FinDto> convertItem = convetToList(object, testBatchList);

    //删除数据库存在的数据
    // deleteData(orderCodes, testBatchList);

    if (!CollectionUtils.isEmpty(object)) {
      String filePath = path.concat(File.separator).concat(fileName);

      CsvWriter csvWriter = new CsvWriter(filePath, ',', StandardCharsets.UTF_8);
      try {
        for (FinDto finDto : object) {
          String[] content = {
              finDto.getCityname(),
              finDto.getBalancetype(),
              finDto.getBalancetypename(),
              finDto.getProjectname(),
              finDto.getStimateprice(),
              finDto.getBond(),
              finDto.getPrepaybond(),
              finDto.getPrepayrent(),
              finDto.getPrepayfreight(),
              finDto.getLogisticsfreight(),
              finDto.getUpdatetime(),
              finDto.getSettlementscope(),
              finDto.getSettlementscopename(),
              finDto.getPaymentratio(),
              finDto.getPaymentrationame(),
              finDto.getAccountPeriod(),
              finDto.getStorecode(),
              finDto.getCreateby(),
              finDto.getCreatename(),
              finDto.getPayercode(),
              finDto.getPayername(),
              finDto.getSignupdatetime(),
              finDto.getOrdercode()
          };
          csvWriter.writeRecord(content);
        }

      } catch (IOException e) {
        log.error(e.getMessage());
      } finally {
        csvWriter.close();
      }
    }

  }

  @Override
  public void generateEsCsvs(String path, String fileName,
      List<com.example.demo1.esEntity.FinDto> object) {
    if (!CollectionUtils.isEmpty(object)) {
      String filePath = path.concat(File.separator).concat(fileName);

      CsvWriter csvWriter = new CsvWriter(filePath, ',', StandardCharsets.UTF_8);
      try {
        for (com.example.demo1.esEntity.FinDto finDto : object) {
          String[] content = {
              finDto.getCityname(),
              finDto.getBalancetype(),
              finDto.getBalancetypename(),
              finDto.getProjectname(),
              finDto.getStimateprice(),
              finDto.getBond(),
              finDto.getPrepaybond(),
              finDto.getPrepayrent(),
              finDto.getPrepayfreight(),
              finDto.getLogisticsfreight(),
              finDto.getUpdatetime(),
              finDto.getSettlementscope(),
              finDto.getSettlementscopename(),
              finDto.getPaymentratio(),
              finDto.getPaymentrationame(),
              finDto.getAccountPeriod(),
              finDto.getStorecode(),
              finDto.getCreateby(),
              finDto.getCreatename(),
              finDto.getPayercode(),
              finDto.getPayername(),
              finDto.getSignupdatetime(),
              finDto.getOrdercode()
          };
          csvWriter.writeRecord(content);
        }

      } catch (IOException e) {
        log.error(e.getMessage());
      } finally {
        csvWriter.close();
      }
    }
  }

  //此处不需要做转换，要不然用流操作会报错
  private List<FinDto> convetToList(List<FinDto> object, List<TestBatch> testBatchList) {
    List<FinDto> convertItem = Lists.newArrayList();
    convertItem.addAll(object);

    if (!CollectionUtils.isEmpty(testBatchList)) {
      convertItem.removeIf(item -> testBatchList.stream().anyMatch(
          testBatch -> StringUtils.equals(item.getOrdercode(), testBatch.getOrdercode())));
    }
    return convertItem;
  }

  private void deleteData(List<String> orderCodes, List<TestBatch> testBatchList) {
    /**
     * 此处因为是文件导入，所以需要将原有数据先删除，然后在插入，不做更新，load datefile方式不能更新数据，
     * 只能插入，若需要更新，需要用事务方式，那样速度慢
     */
    List<String> orderList = testBatchList.parallelStream()
        .filter(testBatch -> StringUtils.isNotBlank(testBatch.getOrdercode()))
        .map(TestBatch::getOrdercode).collect(
            Collectors.toList());

    //获取数据库所有存在的数据
    if (!CollectionUtils.isEmpty(orderCodes)) {
      List<List<String>> orders = CommonUtils.groupList(orderList, SPLIT_NUMBER);
      orders.parallelStream().forEach(list -> {
        testBatchMapper.deteleByOrderCodes(list);
      });
    }
  }

  private void queryData(List<String> orderCodes, List<TestBatch> testBatchList) {
    if (!CollectionUtils.isEmpty(orderCodes)) {
      List<List<String>> resultList = CommonUtils.groupList(orderCodes, SPLIT_NUMBER);
      resultList.forEach(result -> {
        QueryWrapper<TestBatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(TestBatch.ORDERCODE, result);
        testBatchList.addAll(testBatchMapper.selectList(queryWrapper));
      });
    }
  }
}
