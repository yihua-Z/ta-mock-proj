package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * transaction_expectation
 * @author 
 */
@Data
public class TransactionExpectationKey implements ExpectationModel {
    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 同一交易序列码
     */
    private Integer referenceno;

    /**
     * 销售人代码
     */
    private String distributorcode;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new TransactionExpectationKey();
    }
}