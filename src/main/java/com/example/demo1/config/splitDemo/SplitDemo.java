package com.example.demo1.config.splitDemo;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @ClassName SplitDemo
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 11:04
 * @VERSION 1.0
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class SplitDemo {
  @Resource
  private JobBuilderFactory jobBuilderFactory;
  @Resource
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step splitDemo1(){
    return stepBuilderFactory.get("splitDemo1")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
            System.out.println("splitDemo1");
            return RepeatStatus.FINISHED;
          }
        }).build();
  }
  @Bean
  public Step splitDemo2(){
    return stepBuilderFactory.get("splitDemo2")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
            System.out.println("splitDemo2");
            return RepeatStatus.FINISHED;
          }
        }).build();
  }
  @Bean
  public Step splitDemo3(){
    return stepBuilderFactory.get("splitDemo3")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
            System.out.println("splitDemo3");
            return RepeatStatus.FINISHED;
          }
        }).build();
  }

  //创建flow
  @Bean
  public Flow splitDemoFlow1(){
    log.info(Thread.currentThread().getName());
    return new FlowBuilder<Flow>("splitDemoFlow1")
        .start(splitDemo1())
        .build();
  }
  @Bean
  public Flow splitDemoFlow2(){
    return new FlowBuilder<Flow>("splitDemoFlow2")
        .start(splitDemo2())
        .next(splitDemo3())
        .build();
  }

  //创建任务 两个flow并发执行
  @Bean
  public Job splitDemoJob(){
    return jobBuilderFactory.get("splitDemoJob11223332")
        .start(splitDemoFlow1())
        .split(new SimpleAsyncTaskExecutor()).add(splitDemoFlow2())
        .end()
        .build();
  }

}
