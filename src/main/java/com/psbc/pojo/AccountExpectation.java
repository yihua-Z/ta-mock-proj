package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * account_application
 * @author 
 */
@Data
public class AccountExpectation implements Serializable {
    /**
     * 申请单编
     */
    private String appsheetserialno;

    /**
     * 通讯地
     */
    private String address;

    /**
     * 法人代表身份证件代
     */
    private String instrepridcode;

    /**
     * 法人代表证件类
     */
    private String instrepridtype;

    /**
     * 法人代表姓
     */
    private String instreprname;

    /**
     * 个人证件类型及机构证件类
     */
    private String certificatetype;

    /**
     * 投资人证件号
     */
    private String certificateno;

    /**
     * 投资人姓
     */
    private String investorname;

    /**
     * 交易发生日
     */
    private String transactiondate;

    /**
     * 交易发生时
     */
    private String transactiontime;

    /**
     * 个人/机构标
     */
    private String individualorinstitution;

    /**
     * 机构类
     */
    private String institutiontype;

    /**
     * 投资人邮政编
     */
    private String postcode;

    /**
     * 经办人证件号
     */
    private String transactorcertno;

    /**
     * 经办人证件类
     */
    private String transactorcerttype;

    /**
     * 经办人姓
     */
    private String transactorname;

    /**
     * 投资人理财交易账
     */
    private String transactionaccountid;

    /**
     * 销售人代
     */
    private String distributorcode;

    /**
     * 业务代
     */
    private String businesscode;

    /**
     * 对方销售人处投资人理财交易账
     */
    private String targettransactionaccountid;

    /**
     * 理财产品总份数在资金清算机构的交收账
     */
    private String acctnooffminclearingagency;

    /**
     * 理财产品总份数在资金清算机构的交收账户
     */
    private String acctnameoffminclearingagency;

    /**
     * 理财资金清算机构代
     */
    private String clearingagencycode;

    /**
     * 投资人出生日
     */
    private String investorsbirthday;

    /**
     * 投资人在销售人处用于交易的资金账
     */
    private String depositacct;

    /**
     * 交易所在地区编
     */
    private String regioncode;

    /**
     * 投资人学
     */
    private String educationlevel;

    /**
     * 投资人E-MAIL地
     */
    private String emailaddress;

    /**
     * 投资人传真号
     */
    private String faxno;

    /**
     * 投资人职业代
     */
    private String vocationcode;

    /**
     * 投资人住址电
     */
    private String hometelno;

    /**
     * 投资人年收
     */
    private Integer annualincome;

    /**
     * 投资人手机号
     */
    private String mobiletelno;

    /**
     * 多渠道开户标
     */
    private String multiacctflag;

    /**
     * 网点号
     */
    private String branchcode;

    /**
     * 投资人单位电话号
     */
    private String officetelno;

    /**
     * 投资人户名简
     */
    private String accountabbr;

    /**
     * 密函编
     */
    private String confidentialdocumentcode;

    /**
     * 投资人性
     */
    private String sex;

    /**
     * 上交所证券账
     */
    private String shsecuritiesaccountid;

    /**
     * 深交所证券账
     */
    private String szsecuritiesaccountid;

    /**
     * 投资人理财账
     */
    private String taaccountid;

    /**
     * 投资人电话号
     */
    private String telno;

    /**
     * 使用的交易手
     */
    private String tradingmethod;

    /**
     * 未成年人标
     */
    private String minorflag;

    /**
     * 对账单寄送选
     */
    private String delivertype;

    /**
     * 经办人识别方
     */
    private String transactoridtype;

    /**
     * 理财账户卡的凭证
     */
    private String accountcardid;

    /**
     * 对账单寄送方
     */
    private String deliverway;

    /**
     * 投资者国
     */
    private String nationality;

    /**
     * 操作（清算）网点编
     */
    private String netno;

    /**
     * 经纪
     */
    private String broker;

    /**
     * 工作单位名
     */
    private String corpname;

    /**
     * 证件有效日
     */
    private String certvaliddate;

    /**
     * 机构经办人身份证件有效日
     */
    private String insttrancertvaliddate;

    /**
     * 机构法人身份证件有效日
     */
    private String instreprcertvaliddate;

    /**
     * 客户风险等
     */
    private String clientriskrate;

    /**
     * 机构法人经营范
     */
    private String instreprmanagerange;

    /**
     * 控股股
     */
    private String controlholder;

    /**
     * 实际控制
     */
    private String actualcontroller;

    /**
     * 婚姻状
     */
    private String marriagestatus;

    /**
     * 家庭人口
     */
    private Integer familynum;

    /**
     * 家庭资
     */
    private BigDecimal penates;

    /**
     * 媒体偏
     */
    private String mediahobby;

    /**
     * 投资人英文
     */
    private String englishfirstname;

    /**
     * 投资人英文
     */
    private String englishfamliyname;

    /**
     * 行
     */
    private String vocation;

    /**
     * 企业性
     */
    private String corpoproperty;

    /**
     * 员工人
     */
    private Integer staffnum;

    /**
     * 兴趣爱好类
     */
    private String hobbytype;

    /**
     * 省/直辖
     */
    private String province;

    private String city;

    /**
     * 县/
     */
    private String county;

    /**
     * 推荐
     */
    private String commendperson;

    /**
     * 推荐人类
     */
    private String commendpersontype;

    /**
     * 投资人收款银行账户户
     */
    private String acctnameofinvestorinclearingagency;

    /**
     * 投资人收款银行账户账
     */
    private String acctnoofinvestorinclearingagency;

    /**
     * 投资人收款银行账户开户
     */
    private String clearingagency;

    /**
     * 受理方
     */
    private String acceptmethod;

    /**
     * 冻结原
     */
    private String frozencause;

    /**
     * 冻结截止日
     */
    private String freezingdeadline;

    /**
     * TA的原确认流水
     */
    private String originalserialno;

    /**
     * 原申请单编
     */
    private String originalappsheetno;

    /**
     * 摘要/说
     */
    private String specification;

    /**
     * TA代
     */
    private String tacode;

    /**
     * 同一个申请的序列
     */
    private Integer referencenumber;

    private static final long serialVersionUID = 1L;
}