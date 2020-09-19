package com.example.demo1.job;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JobTest$
 *
 * @author shuai
 * @date 2020/4/8$
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

  @Resource
  private JobRepository jobRepository;


  @Test
  public void test(){
      Job job = new SimpleJob();
      job.isRestartable();

      JobParameters jobParameters = new JobParameters();
    try {
      JobExecution jobExecution = jobRepository.createJobExecution("iteamReaderDbJobn",jobParameters);
      jobRepository.update(jobExecution);
    } catch (JobExecutionAlreadyRunningException e) {
      e.printStackTrace();
    } catch (JobRestartException e) {
      e.printStackTrace();
    } catch (JobInstanceAlreadyCompleteException e) {
      e.printStackTrace();
    }
  }
}