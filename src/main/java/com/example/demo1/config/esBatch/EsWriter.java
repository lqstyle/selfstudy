package com.example.demo1.config.esBatch;

import com.example.demo1.esEntity.FinDto;
import com.example.demo1.service.FinDtoService;
import com.example.demo1.service.TestBatchService;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EsWriter implements ItemWriter<FinDto> {

  @Value("${file.path}")
  private String path;

  @Resource
  private FinDtoService finDtoService;

  private AtomicInteger atomicInteger = new AtomicInteger();

  @Resource
  private TestBatchService testBatchService;

  private static final String TEST_BATCH = "testBatch";
  private static final String CSV = ".csv";

  @Override
  public void write(List<? extends FinDto> items) throws Exception {
    long a = System.currentTimeMillis();
    String name = String.valueOf(atomicInteger.incrementAndGet()).concat(CSV);
    String fileName = path.concat(File.separator).concat(TEST_BATCH).concat(name);
    finDtoService.spendEsTime(path, TEST_BATCH.concat(
        name), (List<FinDto>) items);
    long b = System.currentTimeMillis();
    log.info("生成文件耗时：" + (b - a));

    //文件入库
    Path paths = Paths.get(fileName);
    if (paths.toFile().exists()) {
      testBatchService.loadTestBatch(paths.toFile().getAbsolutePath());
    }else{
      log.info("文件不存在:{}",paths.toFile().getAbsolutePath());
    }
  }

}
