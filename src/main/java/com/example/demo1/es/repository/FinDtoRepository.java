package com.example.demo1.es.repository;

import com.example.demo1.esEntity.FinDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * FinDtoRepository$
 *
 * @author shuai
 * @date 2020/4/10$
 */
@Component
public interface FinDtoRepository extends ElasticsearchRepository<FinDto,String> {

}