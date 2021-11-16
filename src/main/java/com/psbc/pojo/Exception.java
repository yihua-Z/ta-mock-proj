package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * exception
 * @author 
 */
@Data
public class Exception extends ExceptionKey implements DatabaseModel {
    /**
     * 交易日期
     */
    private String apptransactiondate;

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


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new Exception();
    }
}