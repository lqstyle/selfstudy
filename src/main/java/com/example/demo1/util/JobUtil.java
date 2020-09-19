package com.example.demo1.util;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;

/**
 * JobUtil$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Slf4j
public class JobUtil {

  private JobUtil(){

  }

  public static void stopJob(JobOperator jobOperator, String jobName) {
    try {
      Set<Long> executions = jobOperator
          .getRunningExecutions(jobName);
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