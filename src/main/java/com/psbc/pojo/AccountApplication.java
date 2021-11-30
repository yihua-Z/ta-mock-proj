package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * account_application
 * @author 
 */
@Data
public class AccountApplication extends AccountApplicationKey implements ApplicationModel {

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
     * 同一交易序列码
     */
    private Integer referenceno;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 法人代表身份证件代码
     */
    private String instrepridcode;

    /**
     * 法人代表证件类型
     */
    private Object instrepridtype;

    /**
     * 法人代表姓名
     */
    private String instreprname;

    /**
     * 个人证件类型及机构证件型
     */
    private Object certificatetype;

    /**
     * 投资人证件号码
     */
    private String certificateno;

    /**
     * 投资人户名
     */
    private String investorname;

    /**
     * 交易发生日期
     */
    private String transactiondate;

    /**
     * 交易发生时间
     */
    private String transactiontime;

    /**
     * 个人/机构标志
     */
    private Object individualorinstitution;

    /**
     * 机构类型
     */
    private Object institutiontype;

    /**
     * 投资人邮政编码
     */
    private String postcode;

    /**
     * 经办人证件号码
     */
    private String transactorcertno;

    /**
     * 经办人证件类型
     */
    private Object transactorcerttype;

    /**
     * 经办人姓名
     */
    private String transactorname;

    /**
     * 投资人理财交易帐号
     */
    private String transactionaccountid;

    /**
     * 业务代码
     */
    private String businesscode;

    /**
     * 对方销售人处投资人基金交易帐号
     */
    private String targettransactionaccountid;

    /**
     * 投资人收款银行账户账号
     */
    private String acctnooffminclearingagency;

    /**
     * 投资人收款银行账户户名
     */
    private String acctnameoffminclearingagency;

    /**
     * 理财资金清算机构代码
     */
    private String clearingagencycode;

    /**
     * 投资人出生日期
     */
    private String investorsbirthday;

    /**
     * 投资人在销售人处用于交易的资金账号
     */
    private String depositacct;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 投资人学历
     */
    private Object educationlevel;

    /**
     * 投资人E-MAIL地址
     */
    private String emailaddress;

    /**
     * 投资人传真号码
     */
    private String faxno;

    /**
     * 投资人职业代码
     */
    private String vocationcode;

    /**
     * 投资人住址电话
     */
    private String hometelno;

    /**
     * 投资人年收入
     */
    private Integer annualincome;

    /**
     * 投资人手机号码
     */
    private String mobiletelno;

    /**
     * 多渠道开户标志
     */
    private Object multiacctflag;

    /**
     * 网点号码
     */
    private String branchcode;

    /**
     * 投资人单位电话号码
     */
    private String officetelno;

    /**
     * 投资人户名简称
     */
    private String accountabbr;

    /**
     * 密函编号
     */
    private String confidentialdocumentcode;

    /**
     * 投资人性别（1-男，2-女）
     */
    private Object sex;

    /**
     * 上交所证券账号
     */
    private String shsecuritiesaccountid;

    /**
     * 深交所证券账号
     */
    private String szsecuritiesaccountid;

    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 投资人电话号码
     */
    private String telno;

    /**
     * 使用的交易手段
     */
    private String tradingmethod;

    /**
     * 未成年人标志
     */
    private Object minorflag;

    /**
     * 对账单寄送选择
     */
    private Object delivertype;

    /**
     * 经办人识别方式
     */
    private Object transactoridtype;

    /**
     * 理财账户卡的凭证号
     */
    private String accountcardid;

    /**
     * 对账单寄送方式
     */
    private String deliverway;

    /**
     * 投资者国籍
     */
    private String nationlity;

    /**
     * 操作（清算）网点编号
     */
    private String netno;

    /**
     * 客户所属的经纪人
     */
    private String broker;

    /**
     * 工作单位名称
     */
    private String corpname;

    /**
     * 证件有效日期
     */
    private String certvaliddate;

    /**
     * 机构经办人身份证件有效日期
     */
    private String insttrancertvaliddate;

    /**
     * 机构法人身份证件有效日期
     */
    private String instreprcertvaliddate;

    /**
     * 客户风险等级
     */
    private String clientriskrate;

    /**
     * 机构法人经营范围
     */
    private String instreprmanagerange;

    /**
     * 控股股东
     */
    private String controlholder;

    /**
     * 实际控制人
     */
    private String actualcontroller;

    /**
     * 婚姻状况
     */
    private String marriagestatus;

    /**
     * 家庭人口数
     */
    private Integer familynum;

    /**
     * 家庭资产
     */
    private BigDecimal penates;

    /**
     * 媒体偏好
     */
    private String mediahobby;

    /**
     * 投资人英文名
     */
    private String englishfirstname;

    /**
     * 投资人英文姓
     */
    private String englishfamliyname;

    /**
     * 行业（采用国标 GB/T4754-2011）
     */
    private String vocation;

    /**
     * 企业性质
     */
    private String corpoproperty;

    /**
     * 员工人数
     */
    private BigDecimal staffnum;

    /**
     * 兴趣爱好类型
     */
    private String hobbytype;

    /**
     * 省/直辖市
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县/区
     */
    private String county;

    /**
     * 推荐人
     */
    private String commendperson;

    /**
     * 推荐人类型
     */
    private Object commendpersontype;

    /**
     * 投资人收款银行账户户名
     */
    private String acctnameofinvestorinclearingagency;

    /**
     * 投资人收款银行账户账号
     */
    private String acctnoofinvestorinclearingagency;

    /**
     * 投资人收款银行账户开户行
     */
    private String clearingagency;

    /**
     * 受理方式
     */
    private String acceptmethod;

    /**
     * 冻结原因
     */
    private Object frozencause;

    /**
     * 冻结截止日期（YYYYMMDD）
     */
    private String freezingdeadline;

    /**
     * TA的原确认流水号
     */
    private String originalserialno;

    /**
     * 原申请单编号
     */
    private String originalappsheetno;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * 记录状态（0-waiting, 1-processing, 2-processed）
     */
    private Object recordstatus;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountApplication();
    }
}