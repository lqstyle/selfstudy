package com.example.demo1.config.flowJob;

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

/**
 * @ClassName MultiStepJobConfiguration
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 9:54
 * @VERSION 1.0
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class FlowJobConfiguration {

  @Resource
  private JobBuilderFactory jobBuilderFactory;

  @Resource
  private StepBuilderFactory stepBuilderFactory;


  //创建bean
  @Bean
  public Job hellowTestJob() {
    return jobBuilderFactory.get("hellowTestJob")
/*        .start(stepOne())  //包含三个step 方法一
        .next(stepTwo())
        .next(stepThree())
        .build();*/
        .start(stepOne())
        .on("COMPLETED").to(stepTwo())
        .from(stepTwo()).on("COMPLETED").to(stepThree())
        .from(stepThree()).end()
        .build();
  }


  @Bean
  public Step stepOne() {
    return stepBuilderFactory.get("stepOne").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        log.info("step 1 process");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

  @Bean
  public Step stepTwo() {
    return stepBuilderFactory.get("stepTwo").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        log.info("step 2 process");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

  @Bean
  public Step stepThree() {
    return stepBuilderFactory.get("stepThree").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        log.info("step 3 process");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

  @Bean
  public Flow flowDemoFlow(){
    return new FlowBuilder<Flow>("flowDemoFlow1")
        .start(stepOne())
        .next(stepTwo())
        .build();
  }
  //创建Job对象
  @Bean
  public Job flowDemoJob(){
    return jobBuilderFactory.get("flowDemoJob134")
        .start(flowDemoFlow())
        .next(stepThree())
        .end()
        .build();
  }
}
