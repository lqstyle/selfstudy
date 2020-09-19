package com.example.demo1.controller;


import com.example.demo1.service.TestLoadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lucas
 * @since 2020-02-24
 */
@Controller
@RequestMapping("/load")
public class TestLoadController {

    @Resource
    private TestLoadService testLoadService;

    @Value("${file.path}")
    private String path;

    @RequestMapping("/t1")
    public void generate() {
        Long begin = System.currentTimeMillis();
        testLoadService.generateCsv(path, "test1.csv", 1000000);
        //testLoadService.generateAllCsv(path, "test1.csv", 100);
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件生成耗时: " + (end - begin) / 1000 + "秒");

        Long begin1 = System.currentTimeMillis();
        testLoadService.loadAllTest();
        Long end1 = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@文件入库耗时: " + (end1 - begin1) / 1000 + "秒");
    }

    @RequestMapping("/t2")
    public void generates() {
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            testLoadService.getAll();
        }
        testLoadService.getAll();
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@查询耗时: " + (end - begin) / 1000 + "秒");
    }


    @RequestMapping("/t3")
    public void generatess() {
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            testLoadService.orgnizateData();
        }
        Long end = System.currentTimeMillis();
        System.out.println("@@@@@@@@@@@数据计算: " + (end - begin) / 1000 + "秒");

    }

    public static void main1(String[] arges) {
        //自定义数组
        ArrayList<ArrayList<String>> alldata = new ArrayList<ArrayList<String>>();

        int random = 0;
        for (int i = 0; i < 40000000; i++) {
            random = (int) (Math.random() * 10);
            alldata.add(new ArrayList<String>(Arrays.asList("lucas" + random, "male", "2" + random, "nj")));  //添加一行
        }
        //保存成csv文件
        Array2CSV(alldata, "C:\\Users\\liangqing\\Desktop\\test.csv");

    }

    public static void Array2CSV(ArrayList<ArrayList<String>> data, String path) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> onerow = data.get(i);
                int k = onerow.size();
                for (int j = 0; j < k; j++) {
                    out.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
                    out.write(DelQuota(onerow.get(j)));
                    if (j != k - 1) {
                        out.write(",");
                    }
                }
                out.newLine();
            }
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String DelQuota(String str) {
        String result = str;
        String[] strQuota = {"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", ";", "'", ",", ".", "/", ":", "/,", "<", ">", "?"};
        for (int i = 0; i < strQuota.length; i++) {
            if (result.indexOf(strQuota[i]) > -1)
                result = result.replace(strQuota[i], "");
        }
        return result;
    }
}

