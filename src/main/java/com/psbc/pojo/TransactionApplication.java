package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * transaction_application
 * @author 
 */
@Data
public class TransactionApplication implements Serializable {
    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 巨额赎回处理标志
     */
    private String largeredemptionflag;

    /**
     * 交易发生日期
     */
    private String transactiondate;

    /**
     * 交易发生时间
     */
    private String transactiontime;

    /**
     * 投资人理财交易账号
     */
    private String transactionaccountid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 申请理财产品份数
     */
    private BigDecimal applicationvol;

    /**
     * 申请金额
     */
    private BigDecimal applicationamount;

    /**
     * 业务代码
     */
    private String businesscode;

    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 销售佣金折扣率
     */
    private BigDecimal discountrateofcommission;

    /**
     * 投资人在销售人处用于交易的资金账号
     */
    private String depositacct;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 结算币种
     */
    private String currencytype;

    /**
     * 网点号码
     */
    private String branchcode;

    /**
     * 原申请单编号
     */
    private String originalappsheetno;

    /**
     * 原申购日期
     */
    private String originalsubsdate;

    /**
     * 个人/机构标志
     */
    private String individualorinstitution;

    /**
     * 交易申请有效天数
     */
    private Integer validperiod;

    /**
     * 预约赎回工作日天数
     */
    private Integer daysredemptioninadvance;

    /**
     * 预约赎回日期
     */
    private String redemptiondateinadvance;

    /**
     * TA的原确认流水号
     */
    private String originalserialno;

    /**
     * 定时定额申购日期
     */
    private String dateofperiodicsubs;

    /**
     * TA确认交易流水号
     */
    private String taserialno;

    /**
     * 定时定额申购期限
     */
    private Integer termofperiodicsubs;

    /**
     * 指定申购日期
     */
    private String futurebuydate;

    /**
     * 对方销售人代码
     */
    private String targetdistributorcode;

    /**
     * 手续费
     */
    private BigDecimal charge;

    /**
     * 对方网点号
     */
    private String targetbranchcode;

    /**
     * 对方销售人处投资人理财交易账号
     */
    private String targettransactionaccountid;

    /**
     * 对方所在地区编号
     */
    private String targetregioncode;

    /**
     * 红利比例
     */
    private BigDecimal dividendratio;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * 转换时的目标理财产品代码
     */
    private String codeoftargetfund;

    /**
     * 交易后端收费总额
     */
    private BigDecimal totalbackendloa;

    /**
     * 收费方式
     */
    private String shareclass;

    /**
     * TA的原确认日期
     */
    private String originalcfmdate;

    /**
     * 明细标志
     */
    private String detailflag;

    /**
     * 原申请日期
     */
    private String originalappdate;

    /**
     * 默认分红方式
     */
    private String defdividendmethod;

    /**
     * 冻结原因
     */
    private String frozencause;

    /**
     * 冻结截止日期
     */
    private String freezingdeadline;

    /**
     * 定时定额品种代码
     */
    private String varietycodeofperiodicsubs;

    /**
     * 定时定额申购序号
     */
    private String serialnoofperiodicsubs;

    /**
     * 定期定额种类
     */
    private String rationtype;

    /**
     * 对方理财账号
     */
    private String targettaaccountid;

    /**
     * 对方TA代码
     */
    private String targetregistrarcode;

    /**
     * 操作（清算）网点编号
     */
    private String netno;

    /**
     * TA客户编号
     */
    private String customerno;

    /**
     * 对方理财产品份额类别
     */
    private String targetsharetype;

    /**
     * 定期定额协议号
     */
    private String rationprotocolno;

    /**
     * 定时定额申购起始日期
     */
    private String begindateofperiodicsubs;

    /**
     * 定时定额申购终止日期
     */
    private String enddateofperiodicsubs;

    /**
     * 定时定额申购每周期发送日
     */
    private Integer senddayofperiodicsubs;

    /**
     * 经纪人
     */
    private String broker;

    /**
     * 促销活动代码
     */
    private String salespromotion;

    /**
     * 受理方式
     */
    private String acceptmethod;

    /**
     * 强制赎回类型
     */
    private String forceredemptiontype;

    /**
     * 带走收益标志
     */
    private String takeincomeflag;

    /**
     * 定投目的
     */
    private String purposeofpesubs;

    /**
     * 定投频率
     */
    private Integer frequencyofpesubs;

    /**
     * 定投周期单位
     */
    private String periodsubtimeunit;

    /**
     * 定投期数
     */
    private BigDecimal batchnumofpesubs;

    /**
     * 资金方式
     */
    private String capitalmode;

    /**
     * 明细资金方式
     */
    private String detailcapitalmode;

    /**
     * 补差费折扣率
     */
    private BigDecimal backenloaddiscount;

    /**
     * 组合编号
     */
    private String combinenum;

    /**
     * 指定认购日期
     */
    private String futuresubscribedate;

    /**
     * 使用的交易手段
     */
    private String tradingmethod;

    /**
     * 巨额购买处理标志
     */
    private String largebuyflag;

    /**
     * 收费类型
     */
    private String chargetype;

    /**
     * 指定费率
     */
    private BigDecimal specifyratefee;

    /**
     * 指定费用
     */
    private BigDecimal specifyfee;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 同一个申请的序列号
     */
    private Integer referencenumber;

    private static final long serialVersionUID = 1L;
}