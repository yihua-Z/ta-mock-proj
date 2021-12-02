package com.psbc.pojo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * fund_para_config
 *
 * @author
 */
@Data
public class FundParaConfig implements DatabaseModel {
    /**
     * 产品参数ID
     */
    private Integer fundparaid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 交易数据下传日期
     */
    private String downloaddate;

    /**
     * 法人追加认购金额
     */
    private BigDecimal instappsubsamnt;

    /**
     * 法人追加认购份数
     */
    private BigDecimal instappsubsvol;

    /**
     * 法人首次认购最低金额
     */
    private BigDecimal minamountbyinst;

    /**
     * 法人首次认购最低份数
     */
    private BigDecimal minvolbyinst;

    /**
     * 个人最高认购份数
     */
    private BigDecimal maxsubsvolbyindi;

    /**
     * 个人最高认购金额
     */
    private BigDecimal maxsubsamountbyindi;

    /**
     * 法人最高认购份数
     */
    private BigDecimal maxsubsvolbyinst;

    /**
     * 法人最高认购金额
     */
    private BigDecimal maxsubsamountbyinst;

    /**
     * 个人认购份数单位
     */
    private BigDecimal unitsubsvolbyindi;

    /**
     * 个人认购金额单位
     */
    private BigDecimal unitsubsamountbyindi;

    /**
     * 法人认购份数单位
     */
    private BigDecimal unitsubsvolbyinst;

    /**
     * 法人认购金额单位
     */
    private BigDecimal unitsubsamountbyinst;

    /**
     * 个人首次申购最低金额
     */
    private BigDecimal minbidsamountbyindi;

    /**
     * 法人首次申购最低金额
     */
    private BigDecimal minbidsamountbyinst;

    /**
     * 个人追加申购最低金额
     */
    private BigDecimal minappbidsamountbyindi;

    /**
     * 法人追加申购最低金额
     */
    private BigDecimal minappbidsamountbyinst;

    /**
     * 个人追加认购份数
     */
    private BigDecimal indiappsubsvol;

    /**
     * 个人追加认购金额
     */
    private BigDecimal indiappsubsamount;

    /**
     * 个人首次认购最低份数
     */
    private BigDecimal minsubsvolbyindi;

    /**
     * 个人首次认购最低金额
     */
    private BigDecimal minsubsamountbyindi;

    /**
     * 个人最大申购金额
     */
    private BigDecimal indimaxpurchase;

    /**
     * 法人最大申购金额
     */
    private BigDecimal instmaxpurchase;

    /**
     * 个人当日累计购买最大金额
     */
    private BigDecimal indidaymaxsumbuy;

    /**
     * 法人当日累计购买最大金额
     */
    private BigDecimal instdaymaxsumbuy;

    /**
     * 个人当日累计赎回最大份额
     */
    private BigDecimal indidaymaxsumredeem;

    /**
     * 法人当日累计赎回最大份额
     */
    private BigDecimal instdaymaxsumredeem;

    /**
     * 个人最大赎回份额
     */
    private BigDecimal indimaxredeem;

    /**
     * 法人最大赎回份额
     */
    private BigDecimal instmaxredeem;

    /**
     * 理财最高赎回份数
     */
    private BigDecimal maxredemptionvol;

    /**
     * 理财最低持有份数
     */
    private BigDecimal minaccountbalance;

    /**
     * 最低理财产品转换份数
     */
    private BigDecimal mininterconvertvol;

    /**
     * 认购方式
     */
    private String substype;

    /**
     * 允许违约赎回标志
     */
    private String allowbreachredempt;

    /**
     * 理财产品转换状态
     */
    private String convertstatus;

    /**
     * 定期定额状态
     */
    private String periodicstatus;

    /**
     * 定期定额申购的金额
     */
    private BigDecimal amountofperiodicsubs;

    /**
     * 定时定额申购日期
     */
    private String dateofperiodicsubs;

    /**
     * 个人发行方式
     */
    private String issuetypebyindi;

    /**
     * 机构发行方式
     */
    private String issuetypebyinst;

    /**
     * 货币式理财万份收益率
     */
    private BigDecimal fundincome;

    /**
     * 货币式理财万份收益正负
     */
    private String fundincomeflag;

    /**
     * 货币式理财七日年收益
     */
    private BigDecimal yield;

    /**
     * 货币式理财七日年收益正负
     */
    private String yieldflag;

    /**
     * 保本净值
     */
    private BigDecimal guaranteednav;

    /**
     * 交易价格
     */
    private BigDecimal tradingprice;

    /**
     * 理财产品面值
     */
    private BigDecimal facevalue;

    /**
     * 公告标志
     */
    private String announcflag;

    /**
     * 默认分红方式
     */
    private String defdividendmethod;

    /**
     * 权益登记日期
     */
    private String registrationdate;

    /**
     * 交易费收取方式
     */
    private String collectfeetype;

    /**
     * 货币式理财年收益率
     */
    private BigDecimal fundyearincomerate;

    /**
     * 货币式理财年收益率正负
     */
    private String fundyearincomerateflag;

    /**
     * 单位净值
     */
    private BigDecimal nav;

    /**
     * 理财产品净值日期
     */
    private String updatedate;

    /**
     * 净值类型（0-普通净值 1-申购净值 2-赎回净值）
     */
    private Object netvaluetype;

    /**
     * 理财产品募集开始日期
     */
    private String ipostartdate;

    /**
     * 理财产品募集结束日期
     */
    private String ipoenddate;

    /**
     * 下一开放日
     */
    private String nexttradedate;

    /**
     * 分红日/发放日
     */
    private String dividentdate;

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
     * 转托管状态
     */
    private String transferagencystatus;

    /**
     * 除权日
     */
    private String xrdate;

    /**
     * 产品价值线数值
     */
    private BigDecimal valueline;

    /**
     * 累积单位分红
     */
    private BigDecimal totaldivident;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new FundParaConfig();
    }
}