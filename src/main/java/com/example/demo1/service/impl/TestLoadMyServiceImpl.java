package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csvreader.CsvWriter;
import com.example.demo1.entity.TestLoadMy;
import com.example.demo1.mapper.TestLoadMyMapper;
import com.example.demo1.service.TestLoadMyService;
import com.example.demo1.solr.ReflectionUtils;
import com.example.demo1.util.CommonUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-02-28
 */
@Service
public class TestLoadMyServiceImpl extends ServiceImpl<TestLoadMyMapper, TestLoadMy> implements TestLoadMyService {

    @Override
    public void getAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("sex", "%ucas5");
        list(queryWrapper);
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
                        CommonUtils.retainTwo((double) (random/5))
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
