package com.example.demo1.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.sql.Timestamp;
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
 * @since 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestBatch extends Model<TestBatch> {

    private static final long serialVersionUID = 1L;

    private String cityName;

    private String balanceType;

    private String balanceTypeName;

    private String projectName;

    private BigDecimal stimatePrice;

    private String bond;

    private String prepayBond;

    private String prepayRent;

    private String prepayFreight;

    private String logisticsFreight;

    private Timestamp updatetime;

    private String settlementScope;

    private String settlementScopeName;

    private String paymentRatio;

    private String paymentRatioName;

    private String accountPeriod;

    private String storeCode;

    private String createby;

    private String createname;

    private String payercode;

    private String payername;

    private Timestamp signupdateTime;

    private String ordercode;

    public static final String  ORDERCODE="ordercode";


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
