package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * prepayment_acct
 * @author 
 */
@Data
public class PrepaymentAcct implements Serializable {
    /**
     * TA代码
     */
    private String tacode;

    /**
     * 源销售人代码
     */
    private String originaldistributorcode;

    /**
     * 目标销售人代码
     */
    private String targetdistributorcode;

    /**
     * 源理财交易账号
     */
    private String originaltransactionaccountid;

    /**
     * 目标理财交易账号
     */
    private String targettransactionaccountid;

    /**
     * 源地区编号
     */
    private String originalregioncode;

    /**
     * 目标地区编号
     */
    private String targetregioncode;

    /**
     * 源网点号
     */
    private String originalbranchcode;

    /**
     * 目标网点号
     */
    private String targetbranchcode;

    /**
     * 过户份额
     */
    private BigDecimal totalvol;

    private static final long serialVersionUID = 1L;
}