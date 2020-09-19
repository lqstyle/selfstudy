package com.example.demo1.mapper;

import com.example.demo1.entity.TestPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
public interface TestPriceMapper extends BaseMapper<TestPrice> {
    void loadPriceTest();
}
