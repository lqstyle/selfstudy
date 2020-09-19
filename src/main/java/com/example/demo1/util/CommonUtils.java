package com.example.demo1.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;

public class CommonUtils {

    private  CommonUtils() {
    }

    public static String retainTwo(Double i){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(i);
    }

    /**
     * @return java.util.List<java.util.List < T>>
     * @Author liangqing
     * @Description // 通过数量进行分组
     * @Date 13:53 2020/3/17
     * @Param [list, quantity]
     */
    public static <T> List<List<T>> groupList(List<T> list, int quantity) {
        List<List<T>> wrapList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        int count = 0;
        while (count < list.size()) {
            wrapList.add(new ArrayList<>(list.subList(count, Math.min((count + quantity), list.size()))));
            count += quantity;
        }
        return wrapList;
    }
}
