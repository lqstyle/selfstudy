package com.example.demo1.enums;

import lombok.Getter;

/**
 * @ClassName SolrCollectionEnum
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 17:13
 * @VERSION 1.0
 */
public enum SolrCollectionEnum {

    SORE_SORE("http://127.0.0.1:8080/solr/", "soreCore0719"),
    NER_CORE("http://127.0.0.1:8080/solr/", "newCore");

    @Getter
    private String name;
    @Getter
    private String url;

    SolrCollectionEnum(String url, String name) {
        this.name = name;
        this.url = url;
    }
}