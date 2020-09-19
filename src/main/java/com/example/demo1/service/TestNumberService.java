package com.example.demo1.service;

import com.example.demo1.entity.TestNumber;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
public interface TestNumberService extends IService<TestNumber> {

    void generateNumberCsv(String path,String fileName,Integer k);

    void loadNumberTest();

    List<TestNumber> getAll();
}
