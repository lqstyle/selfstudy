package com.example.demo1.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TestNumberSolr implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Field
    private String id;

    @Field
    private String number;

    @Field
    private String type;

    @Field
    private String location;

    @Field
    private String comid;

}
