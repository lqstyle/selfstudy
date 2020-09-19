package com.example.demo1.config.multiStep;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
 * @ClassName MultiStepJobConfiguration
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 9:54
 * @VERSION 1.0
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class MultiStepJobConfiguration {

  @Resource
  private JobBuilderFactory jobBuilderFactory;

  @Resource
  private StepBuilderFactory stepBuilderFactory;


  //创建bean
  @Bean
  public Job hellowJob() {
    return jobBuilderFactory.get("hellowJob")
/*        .start(stepOne())  //包含三个step 方法一
        .next(stepTwo())
        .next(stepThree())
        .build();*/
        .start(stepOnes())
        .on("COMPLETED").to(stepTwos())
        .from(stepTwos()).on("COMPLETED").to(stepThrees())
        .from(stepThrees()).end()
        .build();
  }


  @Bean
  public Step stepOnes() {
    return stepBuilderFactory.get("stepOnes").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        log.info("step 1 process");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

  @Bean
  public Step stepTwos() {
    return stepBuilderFactory.get("stepTwos").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        log.info("step 2 process");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

  @Bean
  public Step stepThrees() {
    return stepBuilderFactory.get("stepThrees").tasklet(new Tasklet() {
      @Override
      public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
          throws Exception {
        log.info("step 3 process");
        return RepeatStatus.FINISHED;
      }
    }).build();
  }

}
