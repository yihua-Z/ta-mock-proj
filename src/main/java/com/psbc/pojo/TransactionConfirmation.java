package com.psbc.pojo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * transaction_confirmation
 * @author 
 */
@Data
public class TransactionConfirmation implements ConfirmationModel {
    /**
     * 确认ID
     */
    private Integer confirmid;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    /**
     * 理财账户交易确认份数
     */
    private BigDecimal confirmedvol;

    /**
     * 每笔交易确认金额
     */
    private BigDecimal confirmedamount;

    /**
     * 交易发生日期
     */
    private String transactiondate;

    /**
     * 交易发生时间
     */
    private String transactiontime;

    /**
     * 交易处理返回代码
     */
    private String returncode;

    /**
     * 业务代码
     */
    private String businesscode;

    /**
     * 业务过程完全结束标识
     */
    private String businessfinishflag;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 交易数据下传日期
     */
    private String downloaddate;

    /**
     * 代理费
     */
    private BigDecimal agencyfee;

    /**
     * 单位净值
     */
    private BigDecimal nav;

    /**
     * 其他费用1
     */
    private BigDecimal otherfee1;

    /**
     * 印花税
     */
    private BigDecimal stampduty;

    /**
     * 费率
     */
    private BigDecimal ratefee;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * 转入/转出标识
     */
    private String transferdirection;

    /**
     * 理财账户利息金额
     */
    private BigDecimal interest;

    /**
     * 利息产生的理财产品份数
     */
    private BigDecimal volumebyinterest;

    /**
     * 利息税
     */
    private BigDecimal interesttax;

    /**
     * 交易价格
     */
    private BigDecimal tradingprice;

    /**
     * 税金
     */
    private BigDecimal tax;

    /**
     * 目标理财产品的单位净值
     */
    private BigDecimal targetnav;

    /**
     * 目标理财产品的价格
     */
    private BigDecimal targetfundprice;

    /**
     * 目标理财产品的确认份数
     */
    private BigDecimal cfmvoloftargetfund;

    /**
     * 最少收费
     */
    private BigDecimal minfee;

    /**
     * 其他费用2
     */
    private BigDecimal otherfee2;

    /**
     * 过户费
     */
    private BigDecimal transferfee;

    /**
     * 是否注册登记人发起业务标志
     */
    private String fromtaflag;

    /**
     * 预约赎回标志
     */
    private String redemptioninadvanceflag;

    /**
     * 冻结方式
     */
    private String frozenmethod;

    /**
     * 强行赎回原因
     */
    private String redemptionreason;

    /**
     * 交易确认费用合计
     */
    private BigDecimal totaltransfee;

    /**
     * 最后更新日
     */
    private String alternationdate;

    /**
     * 退款金额
     */
    private BigDecimal refundamount;

    /**
     * 配售比例
     */
    private BigDecimal salepercent;

    /**
     * 实际计算折扣
     */
    private BigDecimal managerrealratio;

    /**
     * 转换费
     */
    private BigDecimal changefee;

    /**
     * 补差费
     */
    private BigDecimal recuperatefee;

    /**
     * 业绩报酬
     */
    private BigDecimal achievementpay;

    /**
     * 业绩补偿
     */
    private BigDecimal achievementcompen;

    /**
     * 份额强制调整标志
     */
    private String sharesadjustmentflag;

    /**
     * 总TA确认流水号
     */
    private String generaltaserialno;

    /**
     * 货币式理财未付收益金额
     */
    private BigDecimal undistributemonetaryincome;

    /**
     * 货币式理财未付收益金额正负
     */
    private String undistributemonetaryincomeflag;

    /**
     * 违约金
     */
    private BigDecimal breachfee;

    /**
     * 违约金归理财资产金额
     */
    private BigDecimal breachfeebacktofund;

    /**
     * 惩罚性费用
     */
    private BigDecimal punishfee;

    /**
     * 转换代理费
     */
    private BigDecimal changeagencyfee;

    /**
     * 补差代理费
     */
    private BigDecimal recuperateagencyfee;

    /**
     * 出错详细信息
     */
    private String errordetail;

    /**
     * 认购期间利息
     */
    private BigDecimal raiseinterest;

    /**
     * 计费人
     */
    private String feecalculator;

    /**
     * 份额注册日期
     */
    private String shareregisterdate;

    /**
     * 理财产品冻结总份数
     */
    private BigDecimal totalfrozenvol;

    /**
     * 理财产品份数余额
     */
    private BigDecimal fundvolbalance;

    /**
     * 冻结金额
     */
    private BigDecimal frozenbalance;

    /**
     * 清算资金经清算人划出日期
     */
    private String transferdatethroughclearingagency;

    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 同一个确认的序列号
     */
    private Integer referencenumber;

    private static final long serialVersionUID = 1L;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new TransactionConfirmation();
    }
}