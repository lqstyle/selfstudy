package com.example.demo1.mapper;

import com.example.demo1.entity.TestLoad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lucas
 * @since 2020-02-24
 */
public interface TestLoadMapper extends BaseMapper<TestLoad> {
    void loadTest();
}
