package com.example.demo1.enums;

import lombok.Getter;

/**
 * EsEnum$
 *
 * @author shuai
 * @date 2020/4/13$ 索引名
 */
public enum EsEnum {

  INDEX_OMS("ordercode", TypeEnum.OMS_TYPE.getTypeName()),
  INDEX_OMSS("ordercode", TypeEnum.V_TYPE.getTypeName());

  @Getter
  String indexName;
  @Getter
  String typeName;

  EsEnum(String indexName, String typeName) {
    this.indexName = indexName;
    this.typeName = typeName;
  }

  /**
   * 索引类型
   */
  private enum TypeEnum {

    OMS_TYPE("vOmsOrderInfoData"),
    V_TYPE("vOmsOrderInfoData1");

    TypeEnum(String typeName) {
      this.typeName = typeName;
    }

    @Getter
    String typeName;
  }

}