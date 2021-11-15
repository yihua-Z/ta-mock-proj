package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * fund_date
 * @author 
 */
@Data
public class FundDateKey implements Serializable {
    /**
     * TA代码
     */
    private String tacode;

    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 对应确认日
     */
    private Integer corrcfmdate;

    private static final long serialVersionUID = 1L;
}