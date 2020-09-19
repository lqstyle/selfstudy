package com.example.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1.entity.FinDto;
import com.example.demo1.entity.TestPrice;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName FinDtoService
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 17:58
 * @VERSION 1.0
 */
public interface FinDtoService extends IService<FinDto> {

  Logger log = LoggerFactory.getLogger(FinDtoService.class);

  void generateCsvs(String path, String fileName, List<FinDto> object);

  void generateEsCsvs(String path, String fileName, List<com.example.demo1.esEntity.FinDto> object);

  default void spendTime(String path, String fileName, List<FinDto> object) {
    long start = System.currentTimeMillis();
    generateCsvs(path, fileName, object);
    long end = System.currentTimeMillis();
    log.info("生成文件花费的时间为：" + (end - start) / 1000);
  }

  default void spendEsTime(String path, String fileName, List<com.example.demo1.esEntity.FinDto> object) {
    long start = System.currentTimeMillis();
    generateEsCsvs(path, fileName, object);
    long end = System.currentTimeMillis();
    log.info("生成文件花费的时间为：" + (end - start) / 1000);
  }

}
