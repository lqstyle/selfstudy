package com.example.demo1.solr;

/**
 * @ClassName SolrIndexException
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 15:44
 * @VERSION 1.0
 */
public class SolrIndexException extends BaseRuntimeException {


    private static final long serialVersionUID = -6665417179847384768L;

    public SolrIndexException(String message, Exception e) {
        super(message, e);
    }

    public SolrIndexException(String message) {
        super(message);
    }

}
