package com.example.demo1.algorithm.order;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liangqing
 * @since 2021/1/6 20:50
 * <p>
 * <p>
 * 1. 有一串字符串String s = "ababab",
 * 这个字符串可以看做由3个"ab"构成,即n=3, L = "ab", s = nL.
 * 现在要求编写一段程序,使用单例模式,输入任意字符串s,输出nL.
 * 如输入: aaaaa 输出 5a ,输入: ababa 输出: 1ababa
 *
 * 单例采用双重检查加锁实现  jdk1.6 以上可以使用这种方式
 */
public class Singleton {

    private static volatile Singleton singleton = null;

    /**
     * 私有化构造器
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


    private String output(String s) {
        String result;
        if (StringUtils.isBlank(s)) {
            result = "你输入为空!";
        } else if (s.contains(" ")) {
            result = "请不要输入空格!";
        } else {
            //获取当前字符串的长度
            int length = s.length();
            int count = 0;
            //循环字符串
            for (int i = 1; i <= length; i++) {
                // 按照指定的长度对数组做分割处理  ababab
                String[] tempArray = s.split(s.substring(0, i));
                //如果分割后为0，则任务已经分割完毕
                if (tempArray.length == 0) {
                    System.out.println("切分到" + i);
                    count = i;
                    break;
                }
            }
            result = length / count + s.substring(0, count);
        }
        return result;
    }

    public static void main(String[] args) {
        Singleton userSingleton = Singleton.getInstance();
        System.out.println("最终输出的结果如下  "+userSingleton.output("ababab"));
        System.out.println("最终输出的结果如下  "+userSingleton.output("aaaaa"));
        System.out.println("最终输出的结果如下  "+userSingleton.output("ababa"));
    }
}
