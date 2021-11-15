package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * acct_reconciliation
 * @author 
 */
@Data
public class AcctReconciliation implements Serializable {
    /**
     * 分红ID
     */
    private Integer reconciliationid;

    /**
     * 持有人可用理财产品份数
     */
    private BigDecimal availablevol;

    /**
     * 理财产品总份数（含冻结）
     */
    private BigDecimal totalvolofdistributorinta;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    /**
     * 理财产品冻结总份数
     */
    private BigDecimal totalfrozenvol;

    /**
     * TA确认交易流水号
     */
    private String taserialno;

    /**
     * 交易后端收费总额
     */
    private BigDecimal totalbackendload;

    /**
     * 收费方式
     */
    private String shareclass;

    /**
     * 明细标志
     */
    private String detailflag;

    /**
     * 份额注册日期
     */
    private String shareregisterdate;

    /**
     * 货币式理财未付收益金额
     */
    private BigDecimal undistributemonetaryincome;

    /**
     * 货币式理财未付收益金额正负
     */
    private String undistributemonetaryincomeflag;

    /**
     * 剩余保本金额
     */
    private BigDecimal guaranteedamount;

    /**
     * 份额原始来源
     */
    private String sourcetype;

    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 销售人代码
     */
    private String distributorcode;

    private static final long serialVersionUID = 1L;
}