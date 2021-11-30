package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_confirmation
 * @author 
 */
@Data
public class AccountConfirmation implements ConfirmationModel {
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

    /**
     * TA确认交易流水号
     */
    private String taserialno;

    /**
     * 是否注册登记人发起业务标志
     */
    private String fromtaflag;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 操作（清算）网点编号
     */
    private Integer netno;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * TA客户编号
     */
    private Integer customerno;

    /**
     * 出错详细信息
     */
    private String errordetail;

    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 申请单编号
     */
    private String appsheetserialno;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountConfirmation();
    }
}