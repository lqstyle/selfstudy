package com.example.demo1.enums;

import lombok.Getter;

/**
 * JobEnum$
 *
 * @author shuai
 * @date 2020/4/9$
 */
public enum JobEnum {

  ITEAMREADER_CSVJOB("iteamReaderCsvJob", "iteamReaderDbStep"),
  ITEAMREADER_CSVESJOB("iteamReaderCsvEsJob", "iteamReaderDbEsStep");


  JobEnum(String job, String step) {
    this.job = job;
    this.step = step;
  }

  @Getter
  private String job;

  @Getter
  private String step;

}