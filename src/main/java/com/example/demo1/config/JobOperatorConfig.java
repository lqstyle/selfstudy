package com.example.demo1.config;

import javax.annotation.Resource;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JobOperatorConfig$
 *
 * @author shuai
 * @date 2020/4/9$
 */
@Configuration
public class JobOperatorConfig {

  @Resource
  private JobLauncher jobLauncher;

  @Resource
  private JobRepository jobRepository;


  @Resource
  private JobExplorer jobExplorer;

  @Resource
  JobRegistry jobRegistry;


  @Bean
  public SimpleJobOperator myJobOperator() {
    SimpleJobOperator operator = new SimpleJobOperator();

    operator.setJobLauncher(jobLauncher);
    // 参数转换
    operator.setJobParametersConverter(new DefaultJobParametersConverter());
    // 持久化方式
    operator.setJobRepository(jobRepository);
    // 任务相关信息
    operator.setJobExplorer(jobExplorer);
    // 注册：任务名字符串和任务对象关联
    operator.setJobRegistry(jobRegistry);
    return operator;
  }
}