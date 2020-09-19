package com.example.demo1.config.solrBatch;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.example.demo1.entity.FinDto;
import com.example.demo1.enums.SolrCollectionEnum;
import com.example.demo1.config.solrBatch.listener.MyJobExecutionListener;
import com.example.demo1.solr.SolrUtil;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/*
 * @ClassName IteamReaderDBDemo
 * @Description solr读取数据，导出csv
 * @Author liangqing
 * @DATE 2020/3/26 11:43
 * @VERSION 1.0
 */
@Configuration
@EnableBatchProcessing
@Slf4j
@Import(DataSourceConfig.class)
public class IteamReaderCsvDemo {

  @Resource
  private JobBuilderFactory jobBuilderFactory;
  @Resource
  private StepBuilderFactory stepBuilderFactory;
  @Resource
  private MyJobExecutionListener myJobExecutionListener;

  @Resource
  private SolrWriter solrWriter;

  @Resource
  private DbWriter dbWriter;

  @Resource
  private JobRepository jobRepository;

  @Resource
  private ItemProcessor<FinDto, FinDto> fristNameLowerCaseProcessor;


  @Bean
  public Job iteamReaderDbJob() {
    return jobBuilderFactory.get("iteamReaderDbJob")
        .incrementer(new RunIdIncrementer())
        //关闭重启机制
        //.preventRestart()
        .start(iteamReaderDbStep())
        .listener(myJobExecutionListener)
        .build();

  }

  @Bean
  public Step iteamReaderDbStep() {
    return stepBuilderFactory.get("iteamReaderDbStep")
        .<FinDto, FinDto>chunk(10000)
        .reader(listItemReader())
        .processor(fristNameLowerCaseProcessor)
        //.writer(dbWriter) // 数据库批量插入 总共耗时16864ms,好判断插入失败数据 solr查询耗时3秒
        .writer(solrWriter) //生成csv文件，用load data file方式入库,4692ms  solr查询耗时3秒
        .build();
  }


  @Bean
  public CompositeItemProcessor<FinDto, FinDto> personDataProcessor() {
    CompositeItemProcessor<FinDto, FinDto> processor = new CompositeItemProcessor<>();
    List<ItemProcessor<FinDto, FinDto>> listProcessor = new ArrayList<>();
    listProcessor.add(fristNameLowerCaseProcessor);
    processor.setDelegates(listProcessor);
    return processor;

  }

  @Bean
  @StepScope
  public ListItemReader<FinDto> listItemReader() {
    long begin = System.currentTimeMillis();
    SolrUtil<FinDto> solrUtil = new SolrUtil<>(
        SolrCollectionEnum.NER_CORE.getUrl().concat(SolrCollectionEnum.NER_CORE.getName()));
    List<FinDto> finDtoList = Lists.newArrayList();
    try {
      finDtoList = solrUtil.search("*:*", FinDto.class, "updatetime", "desc", 1, 400000);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    long end = System.currentTimeMillis();
    log.info("读取solr消耗时间" + (end - begin) / 1000);
    return new ListItemReader<>(finDtoList);
  }
/*
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
  }*/

}
