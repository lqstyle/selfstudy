package com.example.demo1.mapper;

import com.example.demo1.entity.TestNumber;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
public interface TestNumberMapper extends BaseMapper<TestNumber> {
    void loadNumberTest();

    List<TestNumber> getAll();

}
