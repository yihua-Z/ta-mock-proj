package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_expectation
 * @author 
 */
@Data
public class AccountExpectationKey implements Serializable {
    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 同一交易序列码
     */
    private Integer referenceno;

}