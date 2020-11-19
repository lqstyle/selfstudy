package com.example.demo1.concurent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liangqing
 * @since 2020/11/17 14:41
 */

@Slf4j
class MyLocal {
    private static ThreadLocal<List<String>> tt = new ThreadLocal<>();
/*
    private static ThreadLocal<List<Integer>> ii = new ThreadLocal<>();
*/

    public void add(String t, Integer i) {


        List<String> strings = tt.get();
        if(CollectionUtils.isEmpty(strings)){
            List<String> stringss = new ArrayList<>();
            stringss.add(t);
            tt.set(stringss);
        }else{
            strings.add(t);
            tt.set(strings);
        }

       /* List<Integer> integers = ii.get();
        if(CollectionUtils.isEmpty(integers)){
            List<Integer> integers1 = new ArrayList<>();
            integers1.add(i);
            ii.set(integers1);
        }else{
            integers.add(i);
            ii.set(integers);
        }*/


        Map<ThreadLocal, Object> threadLocalMap = ThreadLocalUtil.getThreadLocalMap();
        for(Map.Entry<ThreadLocal, Object> entry :threadLocalMap.entrySet()){
            log.info("当前线程:{},{}" ,Thread.currentThread().getId() ,entry.getValue());
        }
        List<String> strings1 = tt.get();
        System.out.println(strings1.toArray());

    }
}

public class MYThreadlocal {

    public static void main(String[] args) {
        MyLocal myLocal = new MyLocal();
        Thread thread;
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            thread = new Thread(() -> {
                for (int j = 0; j <2 ; j++) {
                    myLocal.add(String.valueOf(j), j);
                }
            });
            thread.start();
        }

    }


}
