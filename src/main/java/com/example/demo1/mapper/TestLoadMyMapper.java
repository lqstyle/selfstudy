package com.example.demo1.mapper;

import com.example.demo1.entity.TestLoadMy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lucas
 * @since 2020-02-28
 */
public interface TestLoadMyMapper extends BaseMapper<TestLoadMy> {

    void loadTest();

}
