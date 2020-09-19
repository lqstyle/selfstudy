package com.example.demo1.config;

import javax.annotation.Resource;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * BatchConfigurerConf$
 *
 * @author shuai
 * @date 2020/4/10$
 */
@Configuration
@EnableBatchProcessing
public class BatchConfigurerConf {

  @Resource
  private JobRepository jobRepository;

  @Bean
  public BatchConfigurer batchConfigurer() {
    return new DefaultBatchConfigurer() {
      @Override
      protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        //开启异步执行
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
      }
    };
  }

}