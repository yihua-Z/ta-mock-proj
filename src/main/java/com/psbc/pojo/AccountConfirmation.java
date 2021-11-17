package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_confirmation
 * @author 
 */
@Data
public class AccountConfirmation implements DatabaseModel {
    /**
     * 确认ID
     */
    private Integer confirmid;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    /**
     * 返回码
     */
    private String returncode;

    /**
     * 交易码
     */
    private String businesscode;

    /**
     * 交易日期
     */
    private String transactiondate;

    /**
     * 交易时间
     */
    private String transactiontime;

    private Integer taserialno;

    private String fromtaflag;

    private String regioncode;

    private Integer netno;

    private String specification;

    private Integer customerno;

    private String errordetail;

    private String taaccountid;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountConfirmation();
    }
}