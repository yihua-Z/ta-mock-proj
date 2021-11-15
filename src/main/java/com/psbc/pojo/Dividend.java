package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * dividend
 * @author 
 */
@Data
public class Dividend implements Serializable {
    /**
     * 分红ID
     */
    private Integer dividendid;

    /**
     * 红利/红利再投资基数
     */
    private BigDecimal basisforcalculatingdividend;

    /**
     * 理财账户红利再投资理财产品份数
     */
    private BigDecimal volofdividendforreinvestment;

    /**
     * 分红日/发放日
     */
    private String dividentdate;

    /**
     * 理财账户红利资金
     */
    private BigDecimal dividendamount;

    /**
     * 每笔交易确认金额
     */
    private BigDecimal confirmedamount;

    /**
     * 交易处理返回代码
     */
    private String returncode;

    /**
     * 投资人理财交易账号
     */
    private String transactionaccountid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 业务代码
     */
    private String businesscode;

    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 单位理财产品分红金额（含税）
     */
    private BigDecimal dividendperunit;

    /**
     * 投资人在销售人处用于交易的资金账号
     */
    private String depositacct;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 交易数据下传日期
     */
    private String downloaddate;

    /**
     * 手续费
     */
    private BigDecimal charge;

    /**
     * 代理费
     */
    private BigDecimal agencyfee;

    /**
     * 理财产品冻结总份数
     */
    private BigDecimal totalfrozenvol;

    /**
     * 网点号码
     */
    private String branchcode;

    /**
     * 其他费用1
     */
    private BigDecimal otherfee1;

    /**
     * 其他费用2
     */
    private BigDecimal otherfee2;

    /**
     * 个人/机构标志
     */
    private String individualorinstitution;

    /**
     * 红利比例
     */
    private BigDecimal dividendratio;

    /**
     * 印花税
     */
    private BigDecimal stampduty;

    /**
     * 冻结金额
     */
    private BigDecimal frozenbalance;

    /**
     * 过户费
     */
    private BigDecimal transferfee;

    /**
     * 收费方式
     */
    private String shareclass;

    /**
     * 计费人
     */
    private String feecalculator;

    /**
     * 分红单位
     */
    private Long drawbonusunit;

    /**
     * 冻结再投资份额
     */
    private BigDecimal frozensharesforreinvest;

    /**
     * 分红类型
     */
    private String dividendtype;

    /**
     * 原申请单编号
     */
    private String originalappsheetno;

    /**
     * 业绩报酬
     */
    private BigDecimal achievementpay;

    /**
     * 业绩补偿
     */
    private BigDecimal achievementcompen;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    /**
     * 理财产品代码
     */
    private String fundcode;

    private static final long serialVersionUID = 1L;
}