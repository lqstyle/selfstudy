package com.example.demo1.service;

import com.example.demo1.entity.TestLoadMy;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-02-28
 */
public interface TestLoadMyService extends IService<TestLoadMy> {

    void getAll();

    void loadAllTest();

    void generateCsv(String path, String fileName, Integer k);



}
