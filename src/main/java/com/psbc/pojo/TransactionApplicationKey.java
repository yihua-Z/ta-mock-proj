package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * transaction_application
 * @author 
 */
@Data
public class TransactionApplicationKey implements Serializable {
    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 同一申请序列号
     */
    private Integer referenceno;

    /**
     * TA代码
     */
    private String tacode;

    private static final long serialVersionUID = 1L;
}