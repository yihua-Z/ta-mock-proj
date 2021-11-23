package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * transaction_application
 * @author 
 */
@Data
public class TransactionApplication extends TransactionApplicationKey implements DatabaseModel {
    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 同一申请序列号
     */
    private Integer referencenumber;

    /**
     * TA代码
     */
    private String tacode;


    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 巨额赎回处理标志(0-取消，1-顺延)
     */
    private Object largeredemptionflag;

    /**
     * 交易发生日期(YYYYMMDD)
     */
    private String transactiondate;

    /**
     * 交易发生时间(HHMMSS)
     */
    private String transactiontime;

    /**
     * 投资人在销售机构内开设的用于交易的账号
     */
    private String transactionaccountid;

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
     * 投资人理财帐号
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
    private Integer originalappsheetno;

    /**
     * 原申购日期
     */
    private String originalsubsdate;

    /**
     * 个人/机构标志(0-机构，1-个人，2-产品)
     */
    private Object individualorinstitution;

    /**
     * 交易申请有效天数
     */
    private Integer validperiod;

    /**
     * 预约赎回工作日天数
     */
    private Integer daysredemptioninadvance;

    /**
     * 预约赎回日期(客户周期产品，购买时上送该字段作为指定到期日) 
     */
    private String redemptiondateinadvance;

    /**
     * TA原确认流水号
     */
    private String originalserialno;

    /**
     * 定期定额申购日期
     */
    private String dateofperiodicsubs;

    /**
     * TA确认交易流水号
     */
    private String taserialno;

    /**
     * 定期定额申购期限
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
    private BigDecimal totalbackendload;

    /**
     * 收费方式
     */
    private Object shareclass;

    /**
     * TA的原确认日期
     */
    private String originalcfmdate;

    /**
     * 明细标志
     */
    private Object detailflag;

    /**
     * 原申请日期
     */
    private String originalappdate;

    /**
     * 默认分红方式
     */
    private Object defdividendmethod;

    /**
     * 冻结原因(0-司法冻结，1-柜台冻结 
2-质押冻结，3-质押、司法双重冻结 4-柜台、司法双重冻结
)
     */
    private Object frozencause;

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
     * 对方理财产品份额类别(0-前收费，1-后收费)
     */
    private Object targetsharetype;

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
     * 定时定额申购每月发送日
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
     * 强制赎回类型(0-强制赎回，1-违约赎回，2-到期)
     */
    private Object forceredemptiontype;

    /**
     * 带走收益标志(0-不带走，1-带走)
     */
    private Object takeincomeflag;

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
    private String detailcapticalmode;

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
    private Object largebuyflag;

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


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new TransactionApplication();
    }
}