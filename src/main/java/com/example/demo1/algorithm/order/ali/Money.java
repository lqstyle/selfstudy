package com.example.demo1.algorithm.order.ali;

import java.math.BigDecimal;

/**
 * @author liangqing
 * @since 2021/1/6 22:21
 */
public class Money {

    private long cent;

    public Money(long cent) {
        this.cent = cent;
    }

    public long getCent() {
        return cent;
    }

    public void setCent(long cent) {
        this.cent = cent;
    }
}
