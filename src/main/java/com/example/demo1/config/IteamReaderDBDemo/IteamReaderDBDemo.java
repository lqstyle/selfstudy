package com.example.demo1.config.IteamReaderDBDemo;

import com.example.demo1.entity.TestPrice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

/**
 * @ClassName IteamReaderDBDemo
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/26 11:43
 * @VERSION 1.0
 */
@Configuration
@EnableBatchProcessing
public class IteamReaderDBDemo {

  @Resource
  private JobBuilderFactory jobBuilderFactory;
  @Resource
  private StepBuilderFactory stepBuilderFactory;
  @Resource
  private DataSource dataSource;
  @Resource
  private DbJdbcWriter dbJdbcWriter;

  @Bean
  public Job iteamReaderDbJob1() {
    return jobBuilderFactory.get("iteamReaderDbJobc1")
        .start(iteamReaderDbSteps())
        .build();

  }

  @Bean
  public Step iteamReaderDbSteps() {
    return stepBuilderFactory.get("iteamReaderDbSteps1")
        .<TestPrice, TestPrice>chunk(1000000)
        .reader(dbJdbcReader())
        .writer(dbJdbcWriter)
        .build();
  }


  @Bean
  @StepScope
  public JdbcPagingItemReader<TestPrice> dbJdbcReader() { //JdbcPagingItemReader是从数据库中筛选
    long begin = System.currentTimeMillis();
    JdbcPagingItemReader<TestPrice> reader = new JdbcPagingItemReader<TestPrice>();
    reader.setDataSource(dataSource);
    reader.setFetchSize(1000000);//每次读1000条
    //把读取的记录转换成User对象
    reader.setRowMapper(new RowMapper<TestPrice>() {
      @Override
      //i表示有多少行
      public TestPrice mapRow(ResultSet resultSet, int i) throws SQLException {
        return TestPrice.builder().id(resultSet.getLong(1))
            .type(resultSet.getString(2))
            .money(resultSet.getBigDecimal(3))
            .comid(resultSet.getLong(4))
            .price(resultSet.getBigDecimal(5))
            .build();
      }
    });
    //指定sql语句
    MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
    provider.setSelectClause("id,type,money,comid,price"); //查什么字段
    provider.setFromClause("from test_price");//从哪个表
    //指定根据那个字段排序
    Map<String, Order> sort = new HashMap<>(1);
    sort.put("id", Order.ASCENDING);
    provider.setSortKeys(sort);

    reader.setQueryProvider(provider);
    long end = System.currentTimeMillis();
    System.out.println("查询百万数据耗时" + (end - begin) / 1000);
    return reader;
  }

}
