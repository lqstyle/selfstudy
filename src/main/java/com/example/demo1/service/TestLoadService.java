package com.example.demo1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1.entity.TestLoad;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lucas
 * @since 2020-02-24
 */
public interface TestLoadService extends IService<TestLoad> {

    void generateAllCsv(String path, String fileName, Integer k);

    void loadAllTest();

    void generateCsv(String path, String fileName, Integer k);

    void getAll();

    List<TestLoad> orgnizateData();
}
