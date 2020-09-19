package com.example.demo1.config.esBatch.listener;

import com.example.demo1.enums.JobEnum;
import com.example.demo1.service.TestBatchService;
import java.time.Instant;
import java.util.Set;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MyJobExecutionListener$
 *
 * @author shuai
 * @date 2020/4/8$
 */
@Slf4j
@Component
public class MyJobEsExecutionListener implements JobExecutionListener {

  @Value("${file.path}")
  private String path;

  @Resource
  private TestBatchService testBatchService;

  @Resource
  private JobOperator jobOperator;

  private Instant begin;

  private Instant end;

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("执行任务前");
    begin = Instant.now();
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("执行任务后");

    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("任务执行成功");
    }

    if (jobExecution.getStatus() != BatchStatus.COMPLETED) {
      log.info("任务执行失败");
    }

   /* List<File> pathList = Lists.newArrayList();
    FileUtils.getFiles(pathList, path);
    if (!CollectionUtils.isEmpty(pathList)) {
      pathList.parallelStream().forEach(path1 -> {
            try {
              testBatchService.loadTestBatch(path1.getAbsolutePath());
            } catch (Exception e) {
              stopJob();
              log.error("【导入数据失败】:当前路径为:{}", path1.getAbsolutePath(), e.getMessage());
            }
          }
      );
    }*/
    log.info("数据导入成功");
    end = Instant.now();
    log.info("总共耗时{}", String.valueOf(end.toEpochMilli() - begin.toEpochMilli()));
  }

  private void stopJob() {
    try {
      Set<Long> executions = jobOperator
          .getRunningExecutions(JobEnum.ITEAMREADER_CSVJOB.getJob());
      try {
        jobOperator.stop(executions.iterator().next());
      } catch (NoSuchJobExecutionException ex) {
        log.error(ex.getMessage());
      } catch (JobExecutionNotRunningException e) {
        log.error(e.getMessage());
      }
    } catch (NoSuchJobException ex) {
      log.error(ex.getMessage());
    }
  }
}