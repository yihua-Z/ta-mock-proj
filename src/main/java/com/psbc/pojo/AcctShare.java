package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * acct_share
 * @author 
 */
@Data
public class AcctShare extends AcctShareKey implements DatabaseModel {
    /**
     * 理财产品总份数（含冻结）
     */
    private BigDecimal totalvolofdistributorinta;

    /**
     * 理财产品总金额（含冻结）
     */
    private BigDecimal totalamountofdistributorinta;

    /**
     * 理财产品冻结总份数
     */
    private BigDecimal totalfrozenvol;

    /**
     * 理财产品冻结总金额
     */
    private BigDecimal totalfrozenamount;

    /**
     * 更新日期
     */
    private String updatedate;

    private static final long serialVersionUID = 1L;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AcctShare();
    }
}