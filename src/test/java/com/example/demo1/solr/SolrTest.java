package com.example.demo1.solr;

import com.example.demo1.entity.FinDto;
import com.example.demo1.enums.SolrCollectionEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName SolrTest
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 16:03
 * @VERSION 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrTest {

    @SuppressWarnings("unchecked")
    @Test
    public void testQuery() throws Exception {
        SolrUtil solrUtil = new SolrUtil(SolrCollectionEnum.NER_CORE.getUrl().concat(SolrCollectionEnum.NER_CORE.getName()));
        Long begin = System.currentTimeMillis();
        List<FinDto> finDtoList = solrUtil.search("*:*", FinDto.class, "updatetime", "desc", 1, 1000000);
        FinDto finDto1 = finDtoList.stream().filter(finDto ->finDto.getOrdercode().equals("ZNLHZL-2018-00330") ).findFirst().get();
        Long end = System.currentTimeMillis();
        System.out.println("数据量" + finDtoList.size());
        System.out.println("此次查询耗时" + (end - begin) / 1000);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testPage() throws Exception {
        SolrUtil solrUtil = new SolrUtil(SolrCollectionEnum.NER_CORE.getUrl().concat(SolrCollectionEnum.NER_CORE.getName()));
        SolrPage<FinDto> solrPage = new SolrPage<>(1, 10);
        SolrPage<FinDto> finDtoSolrPage = solrUtil.searchPage("*:*", FinDto.class, "cityname", "desc", solrPage);
    }
}
