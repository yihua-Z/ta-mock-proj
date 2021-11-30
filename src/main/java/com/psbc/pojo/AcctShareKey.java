package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * acct_share
 * @author 
 */
@Data
public class AcctShareKey implements Serializable {
    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * TA代码
     */
    private String tacode;

    private static final long serialVersionUID = 1L;
}