package com.example.demo1.controller;


import com.example.demo1.service.TestNumberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/number")
public class TestNumberController {

    @Resource
    private TestNumberService testNumberService;

    @Value("${file.path}")
    private String path;

    @RequestMapping("/t1")
    public void generate(String[] args) {
        Long begin = System.currentTimeMillis();
        testNumberService.generateNumberCsv(path, "test2.csv", 1000000);
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件生成耗时: " + (end - begin) / 1000 + "秒");
        Long begin1 = System.currentTimeMillis();
        testNumberService.loadNumberTest();
        Long end1 = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件入库耗时: " + (end1 - begin1) / 1000 + "秒");
    }


    @RequestMapping("/t2")
    public void generates() {
        Long begin = System.currentTimeMillis();
        testNumberService.getAll();
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@查询耗时: " + (end - begin) / 1000 + "秒");
    }

}
