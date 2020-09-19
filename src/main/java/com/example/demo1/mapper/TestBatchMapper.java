package com.example.demo1.mapper;

import com.example.demo1.entity.TestBatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lucas
 * @since 2020-04-08
 */
public interface TestBatchMapper extends BaseMapper<TestBatch> {
  void loadTestBatch(String fileName);

  int deteleByOrderCodes(List<String> orderCode);
}
