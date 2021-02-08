package com.example.demo1.designer.chain.flyweight;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liangqing
 * @since 2021/2/8 11:14
 */
@Data
public class Circle implements Shape {

    private Integer x;

    private Integer y;

    private BigDecimal ratio;

    private String color;

    public Circle(Integer x, Integer y, BigDecimal ratio) {
        this.x = x;
        this.y = y;
        this.ratio = ratio;
    }

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle{" +
                "x=" + x +
                ", y=" + y +
                ", ratio=" + ratio +
                '}');
    }

}
