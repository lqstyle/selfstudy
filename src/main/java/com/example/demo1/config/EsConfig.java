package com.example.demo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * EsConfig$
 *
 * @author shuai
 * @date 2020/4/10$
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.demo1.es.repository")
public class EsConfig {

}