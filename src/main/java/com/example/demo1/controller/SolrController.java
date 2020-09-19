package com.example.demo1.controller;

import com.example.demo1.entity.TestLoad;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/solr")
public class SolrController {

    @Resource
    private SolrClient solrClient;

    //根据di查询
    @RequestMapping("/get/{id}")
    public void getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {

        //根据id查询内容
        SolrDocument solrDocument = solrClient.getById(id);
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.addField("*");
        query.setRows(5000000);
        Long begin = System.currentTimeMillis();
      /*  for (int i = 0; i < 10; i++) {
            QueryResponse queryResponse = solrClient.query(query);
            SolrDocumentList docs = queryResponse.getResults();
        }*/
        QueryResponse queryResponse = solrClient.query(query);
        SolrDocumentList docs = queryResponse.getResults();

        Long end = System.currentTimeMillis();
        System.out.println("solr 查询百万条数据耗时：: " + (end - begin) / 1000 + "秒");

    }

    @RequestMapping("/cc")
    public void compute() throws IOException, SolrServerException {
        Long begin = System.currentTimeMillis();
        computse();
        for (int i = 0; i < 5; i++) {
            computse();
        }
        Long end = System.currentTimeMillis();
        System.out.println("solr 计算数据耗时：: " + (end - begin) / 1000 + "秒");

    }

    private void computse() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.addField("*");
        query.setRows(200000);


        QueryResponse queryResponse = solrClient.query(query);
        SolrDocumentList docs = queryResponse.getResults();
        System.out.println(docs.size());
        List<SolrDocument> list = docs.subList(0, 99999);
        System.out.println(list.size());

        List<TestLoad> testLoads = new ArrayList<TestLoad>();
        TestLoad testLoad;
        for (SolrDocument solrDocument : list) {
            testLoad = new TestLoad();
            testLoad.setSex("男")
                    .setName((String) solrDocument.getFieldValue("name")).setLocation((String) solrDocument.getFieldValue("location"))
                    .setAge((String) solrDocument.getFieldValue("age")).
                    setPrice(String.valueOf(10 * 10));
            testLoads.add(testLoad);
        }
    }
}
