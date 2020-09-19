package com.example.demo1.esEntity;

import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName FinDto
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 15:57
 * @VERSION 1.0
 */
@Data
@Accessors(chain = true)
@Document(indexName = "order_code",type = "vOmsOrderInfo", shards = 1, replicas = 0)
public class FinDto {


    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cityname;

    @Field(type = FieldType.Keyword)
    private String balancetype;

    @Field(type = FieldType.Keyword)
    private String balancetypename;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String projectname;

    @Field(type = FieldType.Keyword)
    private String stimateprice;

    @Field(type = FieldType.Keyword)
    private String bond;

    @Field(type = FieldType.Keyword)
    private String prepaybond;

    @Field(type = FieldType.Keyword)
    private String prepayrent;

    @Field(type = FieldType.Keyword)
    private String prepayfreight;

    @Field(type = FieldType.Keyword)
    private String logisticsfreight;

    @Field(type = FieldType.Date)
    private String updatetime;

    @Field(type = FieldType.Keyword)
    private String settlementscope;

    @Field(type = FieldType.Keyword)
    private String settlementscopename;

    @Field(type = FieldType.Keyword)
    private String paymentratio;

    @Field(type = FieldType.Keyword)
    private String paymentrationame;

    @Field(type = FieldType.Keyword)
    private String accountPeriod;

    @Field(type = FieldType.Keyword)
    private String storecode;

    @Field(type = FieldType.Keyword)
    private String createby;

    @Field(type = FieldType.Keyword)
    private String createname;

    @Field(type = FieldType.Keyword)
    private String payercode;

    @Field(type = FieldType.Keyword)
    private String payername;

    @Field(type = FieldType.Date)
    private String signupdatetime;

    @Id
    private String ordercode;
}
