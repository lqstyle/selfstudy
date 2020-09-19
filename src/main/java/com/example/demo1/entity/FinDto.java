package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @ClassName FinDto
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 15:57
 * @VERSION 1.0
 */
@Data
@Accessors(chain = true)
public class FinDto {
    @Field
    private String cityname;
    @Field
    private String balancetype;
    @Field
    private String balancetypename;
    @Field
    private String projectname;
    @Field
    private String stimateprice;
    @Field
    private String bond;
    @Field
    private String prepaybond;
    @Field
    private String prepayrent;
    @Field
    private String prepayfreight;
    @Field
    private String logisticsfreight;
    @Field
    private String updatetime;
    @Field
    private String settlementscope;
    @Field
    private String settlementscopename;
    @Field
    private String paymentratio;
    @Field
    private String paymentrationame;
    @Field
    private String accountPeriod;
    @Field
    private String storecode;
    @Field
    private String createby;
    @Field
    private String createname;
    @Field
    private String payercode;
    @Field
    private String payername;
    @Field
    private String signupdatetime;

    private String ordercode;
}
