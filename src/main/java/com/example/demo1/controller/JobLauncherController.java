package com.example.demo1.controller;

import com.example.demo1.enums.JobEnum;
import com.example.demo1.util.ApplicationContextUtil;
import com.example.demo1.util.JobUtil;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobLauncherController$
 *
 * @author liangqing
 * @date 2020/4/8$ HttpRequest 启动
 */
@RestController
@RequestMapping("/job")
public class JobLauncherController {

  @GetMapping("/start")
  public void handle()
      throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
    JobParameters jobParameters = new JobParametersBuilder()
        .addDate("date", new Date())
        .toJobParameters();
    JobLauncher jobLauncher = ApplicationContextUtil.getBean(JobLauncher.class);
    jobLauncher.run(ApplicationContextUtil.getBean(JobEnum.ITEAMREADER_CSVJOB.getJob(), Job.class),
        jobParameters);
  }

  @GetMapping("/startEs")
  public void handleEs()
      throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
    JobParameters jobParameters = new JobParametersBuilder()
        .addDate("date", new Date())
        .toJobParameters();
    JobLauncher jobLauncher = ApplicationContextUtil.getBean(JobLauncher.class);
    jobLauncher.run(ApplicationContextUtil.getBean(JobEnum.ITEAMREADER_CSVESJOB.getJob(), Job.class),
        jobParameters);
  }

  @GetMapping("/stop")
  public void stop() {
    JobOperator jobOperator = ApplicationContextUtil.getBean("myJobOperator", JobOperator.class);
    JobUtil.stopJob(jobOperator, JobEnum.ITEAMREADER_CSVJOB.getJob());
  }


}