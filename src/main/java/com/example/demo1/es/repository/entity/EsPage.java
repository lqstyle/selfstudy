package com.example.demo1.es.repository.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * EsPage$
 *
 * @author shuai
 * @date 2020/4/13$
 */
@AllArgsConstructor
@Data
public class EsPage<T> {

  private int startPage;
  private int totalHits;
  private int pageSize;
  private List<T> sourceList;

}