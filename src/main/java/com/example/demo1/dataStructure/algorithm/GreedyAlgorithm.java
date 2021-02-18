package com.example.demo1.dataStructure.algorithm;

import java.util.*;

/**
 * @author liangqing
 * @since 2021/2/17 7:43
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建所有的广播台，放到map中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();

        //将各个电台放到map中
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        //存放选择的电台集合
        List<String> selects = new ArrayList<String>();

        //定义临时变量，存放遍历过程中，电台覆盖的地区和当前没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义给 maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
        String maxKey = null;

        while (allAreas.size() > 0) {

            //每次遍历需要清空maxkey
            maxKey = null;

            for (String broadcast : broadcasts.keySet()) {
                //需要清空临时集合
                tempSet.clear();

                tempSet.addAll(broadcasts.get(broadcast));
                tempSet.retainAll(allAreas);

                //每次取出交集最大的set，贪心算法以局部最优为基础，尽量达到结果最优
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = broadcast;
                }
            }
            if (Objects.nonNull(maxKey)) {
                selects.add(maxKey);
            }
            //移除
            allAreas.removeAll(broadcasts.get(maxKey));
        }

        System.out.println("得到的选择结果是" + selects);
    }
}
