package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * acct_share
 * @author 
 */
@Data
public class AcctShare implements DatabaseModel {
    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 理财产品总份数（含冻结）
     */
    private BigDecimal totalvolofdistributorinta;

    /**
     * 理财产品冻结总份数
     */
    private BigDecimal totalfrozenvol;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AcctShare();
    }
}