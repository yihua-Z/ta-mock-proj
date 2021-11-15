package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * fund_date
 * @author 
 */
@Data
public class FundDate extends FundDateKey implements Serializable {
    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 日期类型
     */
    private String datetype;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    private static final long serialVersionUID = 1L;
}