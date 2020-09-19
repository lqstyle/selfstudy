package com.example.demo1.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
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
@Accessors(chain = true)
public class TestNumber extends Model<TestNumber> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long number;

    private String type;

    private String location;

    private Long comid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
