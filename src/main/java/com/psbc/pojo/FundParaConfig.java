package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * fund_para_config
 * @author 
 */
@Data
public class FundParaConfig implements Serializable {
    /**
     * 产品参数ID
     */
    private Integer fundparaid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 交易数据下传日期
     */
    private String downloaddate;

    /**
     * 产品风险等级
     */
    private String prdriskrate;

    /**
     * 中债产品划分
     */
    private String debtfundtype;

    /**
     * 允许销售的中债客户组别
     */
    private String allowclientgroup;

    /**
     * 理财登记编码
     */
    private String financialregistrationcode;

    /**
     * 销售对象（发售对象）
     */
    private String saleobject;

    /**
     * 是否周期产品
     */
    private String iscyclefund;

    /**
     * 周期天数
     */
    private String cycledays;

    /**
     * 最长周期数
     */
    private String cyclenums;

    /**
     * 默认期数
     */
    private String defcyclenums;

    /**
     * 违约赎回费率
     */
    private BigDecimal breachofcontractfee;

    /**
     * 理财产品类型
     */
    private String lcfundtype;

    /**
     * 产品募集方式
     */
    private String ipotype;

    /**
     * 投资标的（投资性质）
     */
    private String investmenttargets;

    /**
     * 结算天数
     */
    private String cleardays;

    /**
     * 赎回资金到账天数（结算天数）
     */
    private String redeemamtdays;

    /**
     * 分红资金到账天数（结算天数）
     */
    private String divamtdays;

    /**
     * 产品到期资金到账天数结算天数
     */
    private String fundenddays;

    /**
     * 发行失败/比例退款天数
     */
    private String fundissuancefaildays;

    /**
     * 钞汇标志
     */
    private String cashflag;

    /**
     * 产品收益到期日
     */
    private String incomeenddate;

    /**
     * 产品收益币种
     */
    private String incomecurrtype;

    /**
     * 本金返还币种
     */
    private String costcurrtype;

    /**
     * 可选分红方式
     */
    private String choicebonustype;

    /**
     * 购买（认购）撤单
     */
    private String cancelordertype;

    /**
     * 单日申购上限
     */
    private BigDecimal buyupperlimitday;

    /**
     * 允许部分赎回
     */
    private String ispartredeem;

    /**
     * 巨额赎回比例
     */
    private BigDecimal largeredeemratio;

    /**
     * 赎回本金返还方式
     */
    private String redeemamtreturn;

    /**
     * 产品最低募集额
     */
    private String fundminbala;

    /**
     * 产品最高募集额
     */
    private BigDecimal fundmaxbala;

    /**
     * 认购帐务模式
     */
    private String subscribemode;

    /**
     * 申购帐务模式
     */
    private String purchasemode;

    /**
     * 实时赎回资金比例
     */
    private BigDecimal realredeemratio;

    /**
     * 当日实时赎回份额上限
     */
    private BigDecimal realredeemupperlimitofday;

    /**
     * 单人单日快速赎回上限
     */
    private BigDecimal realredeemupperlimitofsolo;

    /**
     * 份额计算方式
     */
    private String sharedomethod;

    /**
     * 对公赎回基数
     */
    private BigDecimal orgredeembase;

    /**
     * 对私赎回基数
     */
    private BigDecimal perredeembase;

    /**
     * 业绩基准
     */
    private BigDecimal comparisonratio;

    /**
     * 产品发行人
     */
    private String fundissuer;

    /**
     * 浮动管理费年化基数
     */
    private String managementbasic;

    /**
     * 超额分层比例
     */
    private BigDecimal excessivertio;

    /**
     * 认购利息年化基础
     */
    private String subbasic;

    /**
     * 认购计息天数
     */
    private String subdelayeddays;

    /**
     * 产品募集账户
     */
    private String fundcollectaccount;

    /**
     * 超额认购比例确认
     */
    private String excessivesubrtio;

    /**
     * 认购利息利率
     */
    private BigDecimal interestrate;

    /**
     * 开市时间
     */
    private String opentime;

    /**
     * 闭市时间
     */
    private String closetime;

    /**
     * 收费方式
     */
    private String shareclass;

    /**
     * 理财产品简称
     */
    private String fundnameabbr;

    /**
     * 理财产品类型
     */
    private String financtype;

    /**
     * 是否保本理财
     */
    private String isguaranteedfund;

    /**
     * 是否LOF
     */
    private String isloffund;

    /**
     * 是否QDII
     */
    private String isqdiifund;

    /**
     * 是否ETF
     */
    private String isetffund;

    /**
     * 赎回费归理财资产比例
     */
    private BigDecimal lxredeemfeebackratio;

    /**
     * 指定赎回方式
     */
    private String redemptionsequence;

    /**
     * 申购金额上限
     */
    private BigDecimal buyupperamount;

    /**
     * 理财产品转换转入金额上限
     */
    private BigDecimal covertinupperamount;

    /**
     * 定时定额申购金额上限
     */
    private BigDecimal periodsubupperamount;

    /**
     * 理财产品成立日期
     */
    private String fundestablishdate;

    /**
     * 交易所标志
     */
    private String exchangeflag;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * 全量标志
     */
    private String wholeflag;

    /**
     * 修改方式
     */
    private String modifyway;

    /**
     * 生效日期
     */
    private String operatedate;

    /**
     * 发行价格
     */
    private BigDecimal issprice;

    /**
     * 产品起息日期
     */
    private String incomedate;

    /**
     * 机构最低持有份额
     */
    private BigDecimal ominaccountbalance;

    /**
     * 个人单笔最少赎回份额
     */
    private BigDecimal pminredemptionvol;

    /**
     * 个人最低产品转换份额
     */
    private BigDecimal pmininterconvertvol;

    /**
     * 机构单笔最少赎回份额
     */
    private BigDecimal ominredemptionvol;

    /**
     * 机构最低产品转换份额
     */
    private BigDecimal omininterconvertvol;

    /**
     * 认购隔日撤单标志
     */
    private String subcancelflag;

    /**
     * 产品收益方式
     */
    private String interestway;

    /**
     * 收益特征
     */
    private String prdprofitkind;

    /**
     * 是否允许实时赎回
     */
    private String isallowprdrealtimeransom;

    /**
     * 产品计息基数
     */
    private String basedays;

    /**
     * 交易方式
     */
    private String transway;

    /**
     * 对应资产池代码
     */
    private String parentsprdcode;

    /**
     * 产品模板号
     */
    private String prdtemplatecode;

    /**
     * 封闭到期日
     */
    private String alimitenddate;

    /**
     * 机构赎回单位
     */
    private BigDecimal oredunit;

    /**
     * 机构单户累计最大购买金额
     */
    private BigDecimal omaxaccuamt;

    /**
     * 个人赎回单位
     */
    private BigDecimal predunit;

    /**
     * 个人单户累计最大购买金额
     */
    private BigDecimal pmaxaccuamt;

    /**
     * 预期收益率说明/业绩比较基准
     */
    private String modelcomment;

    /**
     * 周期清算前N天允许交易
     */
    private String daybeforecfm;

    /**
     * 购买确认起息天数
     */
    private String buyiscontrolconfirmflag;

    /**
     * 赎回确认延迟天数
     */
    private String redeemiscontrolconfirmflag;

    /**
     * 是否零认购成立
     */
    private String zerosetupflag;

    /**
     * 额度控制标志
     */
    private String limitflag;

    /**
     * 折扣率方式
     */
    private String agiotype;

    /**
     * 销售商最大限额
     */
    private BigDecimal sellerlimit;

    /**
     * 锁定期天数
     */
    private Short lockdays;

    /**
     * 是否校验合格投资者
     */
    private String ischeckinvester;

    /**
     * 客户周期模式
     */
    private String clientcyclemode;

    /**
     * 最小周期天数
     */
    private Short mincycledays;

    /**
     * 最大周期天数
     */
    private Short maxcyledays;

    /**
     * 周期期限集合
     */
    private String cycleallotteddays;

    /**
     * 产品申购款过渡户
     */
    private String fundbuyaccount;

    /**
     * TA代码
     */
    private String tacode;

    private static final long serialVersionUID = 1L;
}