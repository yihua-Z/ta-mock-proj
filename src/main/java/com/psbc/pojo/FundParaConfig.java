package com.psbc.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * account_confirmation
 * @author 
 */
@Data
public class FundParaConfig implements Serializable {
    /**
     * 确认ID
     */
    private Integer confirmid;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    /**
     * 交易处理返回代码
     */
    private String returncode;

    /**
     * 业务代码
     */
    private String businesscode;

    /**
     * 交易发生日期
     */
    private String transactiondate;

    /**
     * 交易发生时间
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
    private String netno;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * TA客户编号
     */
    private String customerno;

    /**
     * 出错详细信息
     */
    private String errordetail;

    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 同一个确认的序列号
     */
    private Integer referencenumber;

    private static final long serialVersionUID = 1L;
}