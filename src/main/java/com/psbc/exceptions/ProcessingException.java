package com.psbc.exceptions;

import com.psbc.pojo.Exception;
import lombok.Data;

/**
 * @author Dealyz
 * @version 1.0
 * @date 2021/11/30 11:09
 */
@Data
public class ProcessingException extends Exception {
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
     * 同一记录序列号
     */
    private Integer referenceno;
    /**
     * 交易日期
     */
    private String transactiondate;

    /**
     * 投资人理财交易账号
     */
    private String transactionaccountid;

    /**
     * 业务代码
     */
    private String businesscode;

    /**
     * 错误类型（0-非业务逻辑错误，1-业务逻辑错误）
     */
    private Object errortype;

    /**
     * 错误说明
     */
    private String speification;

    private String returncode;

}
