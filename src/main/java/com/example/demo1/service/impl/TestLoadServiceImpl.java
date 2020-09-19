package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csvreader.CsvWriter;
import com.example.demo1.entity.TestLoad;
import com.example.demo1.entity.TestNumber;
import com.example.demo1.entity.TestPrice;
import com.example.demo1.mapper.TestLoadMapper;
import com.example.demo1.service.TestLoadService;
import com.example.demo1.service.TestNumberService;
import com.example.demo1.service.TestPriceService;
import com.example.demo1.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-02-24
 */
@Service
public class TestLoadServiceImpl extends ServiceImpl<TestLoadMapper, TestLoad> implements TestLoadService {

    private Logger log = LoggerFactory.getLogger(TestLoadServiceImpl.class);

    @Resource
    private TestNumberService testNumberService;
    @Resource
    private TestPriceService testPriceService;


    public void getAll() {
 /*       QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like()*/
        List<TestLoad> loads = list();
    }


    @Override
    public void generateAllCsv(String path, String fileName, Integer k) {
        getAll();
        String filePath = path.concat(File.separator).concat(fileName);

        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        try {
            List<TestLoad> testLoads = orgnizateData();
            testLoads.forEach(testLoad -> {
                String[] content = {
                        testLoad.getName(),
                        testLoad.getSex(),
                        testLoad.getAge(),
                        testLoad.getLocation(),
                        testLoad.getPrice()
                };
                try {
                    csvWriter.writeRecord(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            csvWriter.close();
        }
    }

    public List<TestLoad> orgnizateData() {
        List<TestNumber> testNumbers = testNumberService.getAll();
        List<TestPrice> testPrices = testPriceService.getAll();
        List<TestPrice> testPricesss = testPrices.subList(0,10000);
        List<TestLoad> testLoads = new ArrayList<>();
        TestLoad testLoad;
        for (TestNumber testNumber : testNumbers) {
            testLoad = new TestLoad();
            for (TestPrice testPrice : testPricesss) {
                testLoad.setSex("男")
                        .setName(testNumber.getType()).setLocation(testNumber.getLocation())
                        .setAge(String.valueOf(testNumber.getNumber())).
                        setPrice(String.valueOf(10 * 10));
            }
            testLoads.add(testLoad);
        }

        return testLoads;
    }

    @Override
    public void loadAllTest() {
        super.baseMapper.loadTest();
    }

    @Override
    public void generateCsv(String path, String fileName, Integer k) {

        String filePath = path.concat(File.separator).concat(fileName);

        int random;
        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        try {
            for (int i = 0; i < k; i++) {
                random = (int) (Math.random() * 10);
                String[] content = {
                        String.valueOf(random),
                        "lucas".concat(String.valueOf(random)),
                        "male",
                        String.valueOf(random),
                        "南京",
                        CommonUtils.retainTwo((double) (random / 5))
                };
                csvWriter.writeRecord(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvWriter.close();
        }
    }
}
