package com.example.demo1.solr;

/**
 * @ClassName SolrSearchException
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 15:43
 * @VERSION 1.0
 */
public class SolrSearchException extends BaseRuntimeException {


    private static final long serialVersionUID = -3110813983217788669L;

    public SolrSearchException(String message, Exception e) {
        super(message, e);
    }

    public SolrSearchException(String message) {
        super(message);
    }

}
