package com.example.demo1.config.esBatch;

import com.example.demo1.esEntity.FinDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * FristNameUpperCaseProcessor$
 *
 * @author shuai
 * @date 2020/4/8$
 * 此处实体不能用@builder注解
 */
@Component
@Slf4j
public class EsFristNameLowerCaseProcessor implements ItemProcessor<FinDto,FinDto> {

  @Override
  public FinDto process(FinDto item) throws Exception {
    FinDto finDto = new FinDto();
    return
        finDto.setCityname(item.getCityname())
            .setBalancetype(item.getBalancetype())
            .setBalancetypename(item.getBalancetypename())
            .setProjectname(item.getProjectname())
            .setStimateprice(item.getStimateprice())
            .setBond(item.getBond())
            .setPrepaybond(item.getPrepaybond())
            .setPrepayrent(item.getPrepayrent())
            .setPrepayfreight(item.getPrepayfreight())
            .setLogisticsfreight(item.getLogisticsfreight())
            .setUpdatetime(item.getUpdatetime())
            .setSettlementscope(item.getSettlementscope())
            .setSettlementscopename(item.getSettlementscopename())
            .setPaymentratio(item.getPaymentratio())
            .setPaymentrationame(item.getPaymentrationame())
            .setAccountPeriod(item.getAccountPeriod())
            .setStorecode(item.getStorecode())
            .setCreateby(item.getCreateby())
            .setCreatename(item.getCreatename())
            .setPayercode(StringUtils.isNotBlank(item.getPayercode())?
                item.getPayercode().toLowerCase():
                StringUtils.EMPTY)
            .setPayername(item.getPayername())
            .setSignupdatetime(item.getSignupdatetime())
            .setOrdercode(item.getOrdercode()) ;

  }
}