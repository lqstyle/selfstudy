package com.example.demo1.controller;


import com.example.demo1.service.TestLoadMyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lucas
 * @since 2020-02-28
 */
@Controller
@RequestMapping("/loadMy")
public class TestLoadMyController {

    @Value("${file.path}")
    private String path;


    @Resource
    private TestLoadMyService testLoadMyService;

    @RequestMapping("/t1")
    public void generate() {
        Long begin = System.currentTimeMillis();
        testLoadMyService.generateCsv(path, "test1.csv", 1000000);
        //testLoadService.generateAllCsv(path, "test1.csv", 100);
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件生成耗时: " + (end - begin) / 1000 + "秒");

        Long begin1 = System.currentTimeMillis();
        testLoadMyService.loadAllTest();
        Long end1 = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件入库耗时: " + (end1 - begin1) / 1000 + "秒");
    }

    @RequestMapping("/t2")
    public void generates() {
        Long begin = System.currentTimeMillis();
      /*  for (int i = 0; i < 10; i++) {
            testLoadMyService.getAll();
        }*/
        testLoadMyService.getAll();
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@查询耗时: " + (end - begin) / 1000 + "秒");
    }
}
