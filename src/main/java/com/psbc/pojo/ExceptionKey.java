package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * exception
 * @author 
 */
@Data
public class ExceptionKey extends Throwable implements Serializable {
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

}