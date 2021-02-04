package com.example.demo1.ms;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liangqing
 * @since 2020/12/22 15:06
 * * 假如有一个接口getUersById(userId),返回用户信息的json; 要求getUersById能缓存用户信息，即
 * * 同样的userId请求仅向服务器发送一次,其余从内存中获取,以提高效率。
 * * 高阶要求: 假设有getUersById，getShoolInfoById, getDeviceInfoById …
 * * 都需要缓存结果，提供一个通用的 辅助 函数。
 *
 * 采用桶排序的方法：
 * 1.将不同的小时分为不同的桶，相应把分钟压入桶中。
 * 2.每个桶内部排序一次，计算桶内的最小时间差。
 * 3.把每个桶的第一个元素和前一个非空桶的最后一个元素做计算。
 * 4.注意不要漏掉首个非空桶和最后一个非空桶的首尾元素计算。
 *
 */

class Solution {
    public int findMinDifference(List<String> timePoints) {
        if(timePoints.size() > 1440){
            return 0;
        }
        int[] help = new int[timePoints.size()];
        for (int i = 0; i < help.length ; i++) {
            help[i] = Integer.parseInt(timePoints.get(i).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(i).substring(3, 5));
        }
        Arrays.sort(help);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < help.length; i++) {
            res = Math.min(help[i] - help[i - 1], res);
            if (res == 0) {
                return 0;
            }
        }
        res = Math.min(res, 1440 - help[help.length - 1] + help[0]);
        return res;
    }
}

public class CommonHelper<T> {

    /*
     key 为用户id T为需要返回的信息实体,此处map的初始化大小，根据实际用户体量初始化
     如果不满足最好是2的n次方，若不设置会频繁扩容，影响效率 ，若不是2的n次方，
     hashmap会自动兜底，处理成2的n次方,考虑并发性
     */

    private final Map<String, T> USERCACHE = new ConcurrentHashMap<>(128);

    public T getInfoById(String userId, String type) {

        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        if (!USERCACHE.containsKey(userId)) {
            //不存在则加入缓存
            T t = getDbInfoById(userId, type);
            USERCACHE.put(userId, t);
            return t;
        }
        return USERCACHE.get(userId);
    }

    private T getDbInfoById(String id, String type) {
        T t = null;
        switch (type) {
            case "User":
                //todo 通过id查询用户,并赋值给t
                break;
            case "Shool":
                //todo 通过id查询Shool,并赋值给t
                break;
            case "Device":
                //todo 通过id查询Device,并赋值给t
                break;
            default:
                //todo 兜底
        }
        return t;
    }

}
