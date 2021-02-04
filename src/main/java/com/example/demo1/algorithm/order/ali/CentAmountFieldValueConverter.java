package com.example.demo1.algorithm.order.ali;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liangqing
 * @since 2021/1/6 21:13
 */
public class CentAmountFieldValueConverter {

    //2.判断如下代码片段在具体的业务场景下都有哪些潜在问题，StringUtils、Money内部实现可忽略

    /**
     * <pre>
     *  金额分的转化 例如10000 会转化为 100.00
     *  <code>
     *  CentAmountFieldValueConverter.convert("10000") = 100.00
     *  </code>
     * </pre>
     */


    public void convert(Field field) {
        String value = String.valueOf(field.getDestValue());
        if (StringUtils.isBlank(value)) {
            return;
        }
        try {
            long cent = Long.parseLong(value);
            Money amount = new Money(cent);
            amount.setCent(cent);
            field.setDestValue(amount.toString());
        } catch (Exception e) {

            field.setDestValue(value);
        }

    }


    public static void main(String[] args) {
        CentAmountFieldValueConverter centAmountFieldValueConverter = new CentAmountFieldValueConverter();
        Field field = new Field("10000");
        centAmountFieldValueConverter.convert(field);
    }
}
