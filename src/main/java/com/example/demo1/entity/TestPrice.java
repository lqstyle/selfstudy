package com.example.demo1.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lucas
 * @since 2020-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class TestPrice extends Model<TestPrice> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String type;

    private BigDecimal money;

    private Long comid;

    private BigDecimal price;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
