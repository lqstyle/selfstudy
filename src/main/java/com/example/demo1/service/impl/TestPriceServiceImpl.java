package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csvreader.CsvWriter;
import com.example.demo1.entity.TestNumber;
import com.example.demo1.entity.TestPrice;
import com.example.demo1.mapper.TestPriceMapper;
import com.example.demo1.service.TestPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1.solr.ReflectionUtils;
import com.example.demo1.util.CommonUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
@Service
public class TestPriceServiceImpl extends ServiceImpl<TestPriceMapper, TestPrice> implements TestPriceService {

    @Override
    public void generatePriceCsv(String path, String fileName, Integer k) {
        String filePath = path.concat(File.separator).concat(fileName);

        int random;
        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        try {
            for (int i = 0; i < k; i++) {
                random = (int) (Math.random() * 10);
                String[] content = {
                        "商品",
                        String.valueOf(random*100),
                        "10000",
                        CommonUtils.retainTwo((double) (random/4)),
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
    public void loadPriceTest() {
        super.baseMapper.loadPriceTest();
    }


    @Override
    public List<TestPrice> getAll() {
        QueryWrapper<TestPrice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("money", "%400");
        return list(queryWrapper);
    }

    @Override
    public void generateCsvs(String path, String fileName, List<TestPrice> object) {
        String filePath = path.concat(File.separator).concat(fileName);

        CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        try {
            for(TestPrice testPrice:object){
                String[] content ={
                    testPrice.getId().toString(),
                    testPrice.getType(),
                    testPrice.getMoney().toString(),
                    testPrice.getComid().toString(),
                    testPrice.getPrice().toString()
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTestPrice(TestPrice testPrice) {
        getBaseMapper().insert(testPrice);
        throw  new RuntimeException();
    }

}
