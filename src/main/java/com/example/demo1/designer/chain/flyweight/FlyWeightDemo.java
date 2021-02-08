package com.example.demo1.designer.chain.flyweight;

import java.util.HashMap;

/**
 * @author liangqing
 * @since 2021/2/8 11:19
 */
public class FlyWeightDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            ShapeFactory.getShape("color " + i);
        }

        final HashMap<String, Shape> shapeMap = ShapeFactory.shapeMap;
        System.out.println(shapeMap);
    }
}
