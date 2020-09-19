package com.example.demo1.controller;

import com.example.demo1.esEntity.FinDto;
import com.example.demo1.util.ElasticsearchUtil;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EsController$
 *
 * @author shuai
 * @date 2020/4/11$
 */
@RestController
@RequestMapping("/es")
public class EsController {




  @RequestMapping("/deleteByid")
  public void delete(){
    ElasticsearchUtil.deleteDataById("order_code","vOmsOrderInfo","ZNLHZL-0014-20170029");
  }

  @RequestMapping("/search")
  public void search(){
    QueryBuilder queryBuilder = new MatchAllQueryBuilder();
    ElasticsearchUtil.searchListData("order_code","vOmsOrderInfo",queryBuilder,100000,"balancetype","balancetype","balancetype");
  }


}