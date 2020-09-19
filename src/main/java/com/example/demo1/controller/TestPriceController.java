package com.example.demo1.controller;


import com.example.demo1.service.TestPriceService;
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
 * @since 2020-02-25
 */
@Controller
@RequestMapping("price")
public class TestPriceController {

    @Value("${file.path}")
    private String path;

    @Resource
    private TestPriceService testPriceService;
    @RequestMapping("/t1")
    public void generate(String[] args) {
        Long begin = System.currentTimeMillis();
        testPriceService.generatePriceCsv(path, "test3.csv", 1000000);
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件生成耗时: " + (end - begin) / 1000 + "秒");

        Long begin1 = System.currentTimeMillis();
        testPriceService.loadPriceTest();
        Long end1 = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件入库耗时: " + (end1 - begin1) / 1000 + "秒");
    }

}
