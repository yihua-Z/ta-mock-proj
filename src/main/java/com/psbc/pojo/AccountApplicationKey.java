package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_application
 * @author 
 */
@Data
public class AccountApplicationKey implements Serializable {
    /**
     * 申请单编号
     */
    private String appSheetSerialNo;

    /**
     * TA代码
     */
    private String TACode;

    /**
     * 销售人代码
     */
    private String distributorCode;

    /**
     * 同一交易序列码
     */
    private Integer referenceNo;

}