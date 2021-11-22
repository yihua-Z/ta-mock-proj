package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * fund_info
 * @author 
 */
@Data
public class FundInfo implements Serializable {
    /**
     * 理财产品代码
     */
    private String fundcode;

    /**
     * 理财产品名称
     */
    private String fundname;

    /**
     * 理财产品总份数
     */
    private BigDecimal totalfundvol;

    /**
     * 理财产品状态
     */
    private String fundstatus;

    /**
     * 单位净值
     */
    private BigDecimal nav;

    /**
     * 理财产品净值日期
     */
    private String updatedate;

    /**
     * 净值类型
     */
    private String netvaluetype;

    /**
     * 累计单位净值
     */
    private BigDecimal accumulativenav;

    /**
     * 理财产品转换状态
     */
    private String convertstatus;

    /**
     * 定期定额状态
     */
    private String periodicstatus;

    /**
     * 转托管状态
     */
    private String transferagencystatus;

    /**
     * 理财产品规模
     */
    private BigDecimal fundsize;

    /**
     * 结算币种
     */
    private String currencytype;

    /**
     * 公告标志
     */
    private String announcflag;

    /**
     * 默认分红方式
     */
    private String defdividendmethod;

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
     * 托管人代码
     */
    private String custodiancode;

    /**
     * 定时定额申购的金额
     */
    private BigDecimal amountofperiodicsubs;

    /**
     * 定时定额申购日期
     */
    private String dateofperiodicsubs;

    /**
     * 理财最高赎回份数
     */
    private BigDecimal maxredemptionvol;

    /**
     * 理财最低持有份数
     */
    private BigDecimal minaccountbalance;

    /**
     * 理财产品募集开始日期
     */
    private String ipostartdate;

    /**
     * 理财产品募集结束日期
     */
    private String ipoenddate;

    /**
     * 理财产品总份数
     */
    private String fundmanagercode;

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
     * 注册登记人代码
     */
    private String registrarcode;

    /**
     * 理财发起人
     */
    private String fundsponsor;

    /**
     * 交易价格
     */
    private BigDecimal tradingprice;

    /**
     * 理财产品面值
     */
    private BigDecimal facevalue;

    /**
     * 分红日/发放日
     */
    private String dividentdate;

    /**
     * 权益登记日期
     */
    private String registrationdate;

    /**
     * 除权日
     */
    private String xrdate;

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
     * 理财最少赎回份数
     */
    private BigDecimal minredemptionvol;

    /**
     * 最低理财产品转换份数
     */
    private BigDecimal mininterconvertvol;

    /**
     * 个人发行方式
     */
    private String issuetypebyindi;

    /**
     * 机构发行方式
     */
    private String issuetypebyinst;

    /**
     * 认购方式
     */
    private String substype;

    /**
     * 交易费收取方式
     */
    private String collectfeetype;

    /**
     * 下一开放日
     */
    private String nexttradedate;

    /**
     * 产品价值线数值
     */
    private BigDecimal valueline;

    /**
     * 累积单位分红
     */
    private BigDecimal totaldivident;

    /**
     * 货币式理财万份收益率
     */
    private BigDecimal fundincome;

    /**
     * 货币式理财万份收益正
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
     * 货币式理财年收益率
     */
    private BigDecimal fundyearincomerate;

    /**
     * 货币式理财年收益率正负
     */
    private String fundyearincomerateflag;

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
     * 理财产品当日总收益正负
     */
    private String funddayincomeflag;

    /**
     * 理财产品当日总收益
     */
    private BigDecimal funddayincome;

    /**
     * 允许违约赎回标志
     */
    private String allowbreachredempt;

    /**
     * 理财产品类型
     */
    private String fundtype;

    /**
     * 理财产品类型名称
     */
    private String fundtypename;

    /**
     * 注册登记人名称
     */
    private String registrarname;

    /**
     * 理财产品总份数名称
     */
    private String fundmanagername;

    /**
     * 资管公司客服电话
     */
    private String fundservertel;

    /**
     * 资管公司网站网址
     */
    private String fundinternetaddress;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 销售人代码
     */
    private String distributorcode;

}