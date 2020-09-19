package com.example.demo1.solr;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SolrUtil
 * @Description TODO
 * @Author liangqing
 * @DATE 2020/3/10 15:35
 * @VERSION 1.0
 */
public class SolrUtil<T> {
    // 日志集成
    private final static Logger logger = LoggerFactory.getLogger(SolrUtil.class);

    // 排序类型
    public static final String DESC = "desc";
    public static final String ASC = "asc";

    // 页面最大值
    private static final int MAX = 10000000;

    // 文档主键值
    private static final String SID = "ordercode";


    private volatile static HttpSolrClient solrClient = null;

    public SolrUtil(String url) {
        if (solrClient == null) {
            synchronized (SolrUtil.class) {
                if (null == solrClient) {
                    HttpSolrClient.Builder builder = new HttpSolrClient.Builder(url);
                    solrClient = builder.withConnectionTimeout(10000).withSocketTimeout(60000).build();
                }
            }
        }
    }

    public static void close() throws IOException {
        if (solrClient != null) {
            synchronized (SolrUtil.class) {
                if (null != solrClient) {
                    solrClient.close();
                }
            }
        }
    }

    /**
     * 功能描述：新增索引(新增和修改)
     *
     * @param solr 基础实体对象
     */
    public void createIndex(T entity) {
        Field[] fields = ReflectionUtils.getAllFields(entity.getClass());

        SolrInputDocument doc = new SolrInputDocument();
        for (Field field : fields) {
            doc.addField(field.getName(), ReflectionUtils.invokeGetterMethod(entity, field.getName()));
        }

        try {
            solrClient.add(doc);
            solrClient.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SolrIndexException(e.getMessage());
        }
    }

    /**
     * @throws
     * @Title: createIndex
     * @Description: 批量新增索引
     * @param: @param list
     * @return: void
     */
    public void createIndex(List<T> list) {
        if (list == null || list.size() <= 0) {
            logger.error("集合为空或集合不存在");
            throw new SolrIndexException("集合为空或集合不存在");
        }
        for (T entity : list) {
            this.createIndex(entity);
        }
    }

    /**
     * @throws
     * @Title: deleteIndex
     * @Description: 根据id批量删除指定索引
     * @param: @param id
     * @return: void
     */
    public int deleteIndex(List<String> ids) {
        int status = 0;
        if (ids == null || ids.size() <= 0) {
            return status;
        }
        try {
            UpdateResponse response = solrClient.deleteById(ids);
            status = response.getStatus();
            solrClient.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
            throw new SolrIndexException(e.getMessage());
        }
        return status;
    }

    /**
     * @throws
     * @Title: deleteIndex
     * @Description: 根据query批量删除关联索引
     * @param: @param query
     * @return: void
     */
    public int deleteIndex(String query) {
        int status = 0;
        if (StringUtils.isEmpty(query)) {
            return status;
        }
        try {
            UpdateResponse response = solrClient.deleteByQuery(query);
            status = response.getStatus();
            solrClient.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
            throw new SolrIndexException(e.getMessage());
        }
        return status;
    }

    /**
     * @throws IOException
     * @throws SolrServerException
     * @throws
     * @Title: search
     * @Description: solr 检索查询
     * @param:
     * @return: void
     */
    public List<T> search(String query, Class<?> entity, String sort, String order, Integer start, Integer row) throws Exception {
        List<T> container = new ArrayList<T>();

        SolrQuery solrQuery = new SolrQuery();
        // 设置查询条件
        if (StringUtils.isNoneBlank(query)) {
            solrQuery.setQuery(query);
        }
        // 设置查询属性
        StringBuilder builder = new StringBuilder();
        Field[] fields = ReflectionUtils.getAllFields(entity);
        for (Field field : fields) {
            builder.append(field.getName()).append(",");
        }
        String field = builder.toString();
        field = field.substring(0, field.length() - 1);
        solrQuery.setFields(field);
        // 设置排序
        if (!StringUtils.isEmpty(sort)) {
            if (SolrUtil.DESC.equalsIgnoreCase(order)) {
                solrQuery.setSort(sort, SolrQuery.ORDER.desc);
            }
            if (SolrUtil.ASC.equalsIgnoreCase(order)) {
                solrQuery.setSort(sort, SolrQuery.ORDER.asc);
            }
        }
        // 设置查询页码和记录数
        if (start < 0) {
            start = 0;
        }
        if (start > MAX) {
            start = MAX;
        }
        solrQuery.setStart(start);
        solrQuery.setRows(row < 0 ? 0 : row);


        // solr 检索
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList docs = response.getResults();
        if (CollectionUtils.isEmpty(docs)) {
            return null;
        }
        for (SolrDocument doc : docs) {
            T obj = (T) entity.newInstance();
            ArrayList<Field> searchFields = new ArrayList<Field>(Arrays.asList(ReflectionUtils.getAllFields(obj.getClass())));
            for (Field searchField : searchFields) {
                String propertyName = searchField.getName();
                String propertyValue = (String) doc.getFieldValue(propertyName);
                Class<?> propertyClass = searchField.getType();
                if (propertyClass.equals(Integer.class)) {
                    Integer value = Integer.valueOf(propertyValue);
                    ReflectionUtils.invokeSetterMethod(obj, propertyName, value);
                } else {
                    ReflectionUtils.invokeSetterMethod(obj, propertyName, propertyValue);
                }
            }
            container.add(obj);
        }
        return container;
    }

    /**
     * @throws
     * @Title: searchPage
     * @Description: solr 分页检索
     * @param: @param query
     * @param: @param entity
     * @param: @param sort
     * @param: @param order
     * @param: @param solrPage
     * @param: @return
     * @param: @throws Exception
     * @return: SolrPage<T>
     */
    @SuppressWarnings("unchecked")
    public SolrPage<T> searchPage(String query, Class<?> entity, String sort, String order, SolrPage<T> solrPage) {
        List<T> container = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询条件
        solrQuery.setQuery(query);
        // 设置查询属性
        StringBuilder builder = new StringBuilder();
        Field[] fields = ReflectionUtils.getAllFields(entity);
        for (Field field : fields) {
            builder.append(field.getName()).append(",");
        }
        String field = builder.toString();
        field = field.substring(0, field.length() - 1);
        solrQuery.setFields(field);
        // 设置排序
        if (!StringUtils.isEmpty(sort)) {
            if (SolrUtil.DESC.equalsIgnoreCase(order)) {
                solrQuery.setSort(sort, SolrQuery.ORDER.desc);
            }
            if (SolrUtil.ASC.equalsIgnoreCase(order)) {
                solrQuery.setSort(sort, SolrQuery.ORDER.asc);
            }
        }
        // 设置查询页码和记录数
        int row = solrPage.getPageSize();
        int size = solrPage.getCurrentPage();
        if (size < 0) {
            size = 0;
        }
        if (size > MAX) {
            size = MAX;
        }
        solrQuery.setStart(size);
        solrQuery.setRows(Math.max(0, row));


        // solr 检索
        QueryResponse response = null;
        try {
            response = solrClient.query(solrQuery);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SolrSearchException("search ", e);
        }
        SolrDocumentList docs = null;
        if (null != response && !CollectionUtils.isEmpty(response.getResults())) {
            docs = response.getResults();
        }

        if (CollectionUtils.isEmpty(docs)) {
            return null;
        }
        docs.forEach(doc -> {
            T obj = (T) entity;
            ArrayList<Field> searchFields = new ArrayList<>(Arrays.asList(ReflectionUtils.getAllFields(obj.getClass())));
            searchFields.forEach(searchField -> {
                String propertyName = searchField.getName();
                String propertyValue = (String) doc.getFieldValue(propertyName);
                Class<?> propertyClass = searchField.getType();
                if (propertyClass.equals(Integer.class)) {
                    Integer value = Integer.valueOf(propertyValue);
                    ReflectionUtils.invokeSetterMethod(obj, propertyName, value);
                } else {
                    ReflectionUtils.invokeSetterMethod(obj, propertyName, propertyValue);
                }
            });
            container.add(obj);
        });
        return new SolrPage(solrQuery.getStart(), solrQuery.getRows(), (int) docs.getNumFound(), container);
    }

    /**
     * @throws
     * @Title: searchPageHighlight
     * @Description: solr 高亮分页查询
     * @param: @return
     * @return: SolrPage<T>
     */
    public SolrPage<T> searchPageHighlight(String query, Class<?> entity, String sort, String order, SolrPage<T> solrPage, String highLight) throws Exception {
        List<T> container = new ArrayList<T>();
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询条件
        solrQuery.setQuery(query);
        // 设置查询属性
        StringBuilder builder = new StringBuilder();
        Field[] fields = ReflectionUtils.getAllFields(entity);
        for (Field field : fields) {
            builder.append(field.getName()).append(",");
        }
        String field = builder.toString();
        field = field.substring(0, field.length() - 1);
        solrQuery.setFields(field);
        // 设置排序
        if (!StringUtils.isEmpty(sort)) {
            if (SolrUtil.DESC.equalsIgnoreCase(order)) {
                solrQuery.setSort(sort, SolrQuery.ORDER.desc);
            }
            if (SolrUtil.ASC.equalsIgnoreCase(order)) {
                solrQuery.setSort(sort, SolrQuery.ORDER.asc);
            }
        }
        // 设置查询页码和记录数
        int row = solrPage.getPageSize();
        int size = solrPage.getCurrentPage();
        if (size < 0) {
            size = 0;
        }
        if (size > MAX) {
            size = MAX;
        }
        solrQuery.setStart(size);
        solrQuery.setRows(Math.max(0, row));

        // 高亮查询
        if (!StringUtils.isEmpty(highLight.trim())) {
            // 开启高亮提示
            solrQuery.setHighlight(true);
            // 设置高亮字段
            solrQuery.addHighlightField(highLight.trim());
            // 设置高亮前缀
            solrQuery.setHighlightSimplePre("<font color='red'>");
            // 设置高亮后缀
            solrQuery.setHighlightSimplePost("</font>");

        }


        // solr 检索
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList docs = response.getResults();
        if (CollectionUtils.isEmpty(docs)) {
            return null;
        }

        // 获取高亮结果
        Map<String, Map<String, List<String>>> highLightMap = null;
        if (!StringUtils.isEmpty(highLight.trim())) {
            highLightMap = response.getHighlighting();
        }
        Map<String, List<String>> map = null;

        for (SolrDocument doc : docs) {
            T obj = (T) entity;
            ArrayList<Field> searchFields = new ArrayList<Field>(Arrays.asList(ReflectionUtils.getAllFields(obj.getClass())));
            for (Field searchField : searchFields) {
                String propertyName = searchField.getName();
                String propertyValue = (String) doc.getFieldValue(propertyName);
                Class<?> propertyClass = searchField.getType();
                // 高亮字段存在
                if (!StringUtils.isEmpty(highLight.trim())) {
                    // 判断反射字段是否与高亮字段相等
                    if (propertyName.equalsIgnoreCase(highLight.trim()) && highLightMap != null) {
                        // 反射字段类型为Integer 不高亮显示
                        if (propertyClass.equals(Integer.class)) {
                            Integer value = Integer.valueOf(propertyValue);
                            ReflectionUtils.invokeSetterMethod(obj, propertyName, value);
                            // 放射字段类型为其他时，高亮显示
                        } else {
                            map = highLightMap.get(doc.getFieldValue(SID));
                            String value = map.get(propertyName).get(0);
                            ReflectionUtils.invokeSetterMethod(obj, propertyName, value);
                        }
                        // 判断反射字段是否与高亮字段不相等
                    } else {
                        if (propertyClass.equals(Integer.class)) {
                            Integer value = Integer.valueOf(propertyValue);
                            ReflectionUtils.invokeSetterMethod(obj, propertyName, value);
                        } else {
                            ReflectionUtils.invokeSetterMethod(obj, propertyName, propertyValue);
                        }
                    }
                    // 高亮字段不存在
                } else {
                    if (propertyClass.equals(Integer.class)) {
                        Integer value = Integer.valueOf(propertyValue);
                        ReflectionUtils.invokeSetterMethod(obj, propertyName, value);
                    } else {
                        ReflectionUtils.invokeSetterMethod(obj, propertyName, propertyValue);
                    }
                }


            }
            container.add(obj);
        }
        return new SolrPage(solrQuery.getStart(), solrQuery.getRows(), (int) docs.getNumFound(), container);
    }
}
