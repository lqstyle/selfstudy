package com.example.demo1.config.esBatch;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.example.demo1.config.solrBatch.listener.FinDtoProcessListener;
import com.example.demo1.config.solrBatch.listener.FinDtoReadListener;
import com.example.demo1.config.solrBatch.listener.FinDtoWriteListener;
import com.example.demo1.config.solrBatch.listener.MyJobExecutionListener;
import com.example.demo1.enums.EsEnum;
import com.example.demo1.es.repository.FinDtoRepository;
import com.example.demo1.esEntity.FinDto;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

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
public class EsIteamReaderCsvEsDemo {

  @Resource
  private JobBuilderFactory jobBuilderFactory;
  @Resource
  private StepBuilderFactory stepBuilderFactory;
  @Resource
  private MyJobExecutionListener myJobExecutionListener;

  @Resource
  private FinDtoRepository finDtoRepository;

  @Resource
  private EsWriter esWriter;

  @Resource
  private DbEsWriter dbEsWriter;

  @Resource
  private JobRepository jobRepository;

  @Resource
  private FinDtoWriteListener finDtoWriteListener;

  @Resource
  private FinDtoReadListener finDtoReadListener;

  @Resource
  private ItemProcessor<FinDto, FinDto> esFristNameLowerCaseProcessor;

  @Resource
  private FinDtoProcessListener finDtoProcessListener;

  @Resource
  private ElasticsearchTemplate elasticsearchTemplate;

  @Resource
  private ElasticsearchTemplate esTemplate;


  @Bean
  public Job iteamReaderCsvEsJob() {
    return jobBuilderFactory.get("iteamReaderCsvEsJob")
        .incrementer(new RunIdIncrementer())
        //关闭重启机制
        //.preventRestart()
        .start(iteamReaderDbEsStep())
        .listener(myJobExecutionListener)
        .build();

  }

  @Bean
  public Step iteamReaderDbEsStep() {
    return stepBuilderFactory.get("iteamReaderDbEsStep")
        .<FinDto, FinDto>chunk(10000)
        .reader(listEsItemReader())
        .processor(esFristNameLowerCaseProcessor)
        //.writer(dbWriter) // 数据库批量插入 总共耗时16864ms,好判断插入失败数据 solr查询耗时3秒
        .writer(esWriter) //生成csv文件，用load data file方式入库,4692ms  solr查询耗时3秒
        .listener(finDtoWriteListener) //可以定义每次写数据的监听 ，读取和处理都可以设置监听
        .listener(finDtoReadListener) //可以定义每次读取数据的监听
        .listener(finDtoProcessListener) // 以定义每次处理数据的监听
        .build();
  }


  @Bean
  public CompositeItemProcessor<FinDto, FinDto> personDataEsProcessor() {
    CompositeItemProcessor<FinDto, FinDto> processor = new CompositeItemProcessor<>();
    List<ItemProcessor<FinDto, FinDto>> listProcessor = new ArrayList<>();
    listProcessor.add(esFristNameLowerCaseProcessor);
    processor.setDelegates(listProcessor);
    return processor;

  }

  @Bean
  @StepScope
  public ListItemReader<com.example.demo1.esEntity.FinDto> listEsItemReader() {
    long begin = System.currentTimeMillis();
    List<FinDto> finDtos = demo();
    long end = System.currentTimeMillis();
    log.info("读取es消耗时间" + (end - begin) / 1000);
    return new ListItemReader<>(finDtos);
  }

  /**
   * 用于将Scroll获取到的结果，处理成dto列表，做复杂映射
   */
  private final SearchResultMapper searchResultMapper = new SearchResultMapper() {
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass,
        Pageable pageable) {
      List<FinDto> result = new ArrayList<>();
      for (SearchHit hit : response.getHits()) {
        if (response.getHits().getHits().length <= 0) {
          return new AggregatedPageImpl<T>(Collections.EMPTY_LIST, pageable,
              response.getHits().getTotalHits(), response.getScrollId());
        }
        //可以做更复杂的映射逻辑
        FinDto finDto = JSONObject
            .parseObject(JSONObject.toJSONString(hit.getSourceAsMap()), FinDto.class);

        result.add(finDto);
      }
      if (result.isEmpty()) {
        return new AggregatedPageImpl<T>(Collections.EMPTY_LIST, pageable,
            response.getHits().getTotalHits(), response.getScrollId());
      }
      return new AggregatedPageImpl<T>((List<T>) result, pageable,
          response.getHits().getTotalHits(), response.getScrollId());
    }

    @Override
    public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
      return null;
    }
  };


  public List<FinDto> demo() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withIndices(EsEnum.INDEX_OMS.getIndexName())//索引名
        .withTypes(EsEnum.INDEX_OMS.getTypeName())//类型名
        .withPageable(PageRequest.of(0, 10000))//从0页开始查，每页10000个结果
        .build();

    ScrolledPage<FinDto> scroll = esTemplate
        .startScroll(1000000, searchQuery, FinDto.class, searchResultMapper);
    log.info("查询总命中数：{}" , scroll.getTotalElements());
    List<FinDto> finDtos = Lists.newLinkedList();
    while (scroll.hasContent()) {
      finDtos.addAll(scroll.getContent());
      //取下一页，scrollId在es服务器上可能会发生变化，需要用最新的。发起continueScroll请求会重新刷新快照保留时间
      scroll =  esTemplate
          .continueScroll(scroll.getScrollId(), 1000000, FinDto.class,
              searchResultMapper);
    }
    //及时释放es服务器资源
    esTemplate.clearScroll(scroll.getScrollId());
    return finDtos;
  }


}
