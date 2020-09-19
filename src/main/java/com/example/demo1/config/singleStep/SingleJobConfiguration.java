package com.example.demo1.config.singleStep;

import javax.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JobConfiguration
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/25 17:34
 * @VERSION 1.0
 */
@Configuration
@EnableBatchProcessing
public class SingleJobConfiguration {

  @Resource
  private JobBuilderFactory jobBuilderFactory;

  @Resource
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job HellowFin() {
    return jobBuilderFactory.get("hellowFin").start(singleStep()).build();
  }

  @Bean
  public Step singleStep() {
    return stepBuilderFactory.get("singleStep").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        System.out.println("hellow fms batch");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }


}
