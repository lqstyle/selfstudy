package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csvreader.CsvWriter;
import com.example.demo1.entity.TestNumber;
import com.example.demo1.mapper.TestNumberMapper;
import com.example.demo1.service.TestNumberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
@Service
public class TestNumberServiceImpl extends ServiceImpl<TestNumberMapper, TestNumber> implements TestNumberService {

    @Override
    public void generateNumberCsv(String path, String fileName, Integer k) {
        String filePath = path.concat(File.separator).concat(fileName);

        int random;
        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        try {
            for (int i = 0; i < k; i++) {
                random = (int) (Math.random() * 10);
                String[] content = {
                        String.valueOf(random),
                        "test",
                        "nj",
                        "10000"
                };
                csvWriter.writeRecord(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvWriter.close();
        }
    }

    @Override
    public void loadNumberTest() {
        super.baseMapper.loadNumberTest();
    }

    @Override
    public List<TestNumber> getAll() {
        QueryWrapper<TestNumber> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("number", "%1");
        return list(queryWrapper);
    }
}
