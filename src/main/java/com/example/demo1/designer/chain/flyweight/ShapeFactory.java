package com.example.demo1.designer.chain.flyweight;

import java.util.HashMap;

/**
 * @author liangqing
 * @since 2021/2/8 11:16
 * 享元工厂
 * 享元模式属于结构性设计模式
 * 减少对象的创建和内存消耗
 *
 * JAVA 中的 String，如果有则返回，如
 * 果没有则创建一个字符串保存在字符串缓存池里面。
 * 2、数据库的数据池
 */
public class ShapeFactory {

    public static final HashMap<String, Shape> shapeMap = new HashMap<>();

    private static final String s = "";

    private static final Object o = new Object();

    public static Shape getShape(String color) {
        Circle circle = (Circle) shapeMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            shapeMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
