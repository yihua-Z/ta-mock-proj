package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.psbc.business.service.annotation.Validator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    @Validator(message = "同一交易序列码", rule = "RE", content = "[0-9]+")
    private Integer referenceno;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 法人代表身份证件代码
     */
    @Validator(message = "法人代表身份证件代码", rule = "RE", content = "[0-9A-Za-z]+")
    private String instrepridcode;

    /**
     * 法人代表证件类型
     */
    @Validator(message = "法人代表证件类型", rule = "RE", content = "[0-9A-L]")
    private Object instrepridtype;

    /**
     * 法人代表姓名
     */
    private String instreprname;

    /**
     * 个人证件类型及机构证件型
     */
    @Validator(message = "个人证件类型及机构证件型", rule = "RE", content = "[0-9A-Za-k]")
    private Object certificatetype;

    /**
     * 投资人证件号码
     */
    @Validator(message = "投资人证件号码", rule = "RE", content = "[0-9A-Za-z]+")
    private String certificateno;

    /**
     * 投资人户名
     */
    private String investorname;

    /**
     * 交易发生日期
     */
    @Validator(message = "交易发生日期", rule = "date")
    private String transactiondate;

    /**
     * 交易发生时间
     */
    @Validator(message = "交易发生时间", rule = "time")
    private String transactiontime;

    /**
     * 个人/机构标志
     */
    @Validator(message = "个人/机构标志", rule = "RE", content = "[0-2]")
    private Object individualorinstitution;

    /**
     * 机构类型
     */
    @Validator(message = "机构类型", rule = "RE", content = "[0-9A-Q]")
    private Object institutiontype;

    /**
     * 投资人邮政编码
     */
    @Validator(message = "投资人邮政编码", rule = "RE", content = "[0-9]+")
    private String postcode;

    /**
     * 经办人证件号码
     */
    @Validator(message = "经办人证件号码", rule = "RE", content = "[0-9A-Za-z]+")
    private String transactorcertno;

    /**
     * 经办人证件类型
     */
    @Validator(message = "经办人证件类型", rule = "RE", content = "[0-9A-B]")
    private Object transactorcerttype;

    /**
     * 经办人姓名
     */
    private String transactorname;

    /**
     * 投资人理财交易帐号
     */
    @Validator(message = "投资人理财交易帐号", rule = "RE", content = "[0-9A-Za-z]+")
    private String transactionaccountid;

    /**
     * 业务代码
     */
    @Validator(message = "业务代码", rule = "RE", content = "[0-9]{3}")
    private String businesscode;

    /**
     * 对方销售人处投资人基金交易帐号
     */
    @Validator(message = "对方销售人处投资人基金交易帐号", rule = "RE", content = "[0-9A-Za-z]+")
    private String targettransactionaccountid;

    /**
     * 投资人收款银行账户账号
     */
    @Validator(message = "投资人收款银行账户账号", rule = "RE", content = "[0-9]+")
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
    @Validator(message = "投资人出生日期", rule = "date")
    private String investorsbirthday;

    /**
     * 投资人在销售人处用于交易的资金账号
     */
    @Validator(message = "投资人在销售人处用于交易的资金账号", rule = "RE", content = "[0-9]+")
    private String depositacct;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 投资人学历
     */
    @Validator(message = "投资人学历", rule = "RE", content = "0[1-9]")
    private Object educationlevel;

    /**
     * 投资人E-MAIL地址
     */
    @Validator(message = "投资人E-MAIL地址", rule = "RE", content = "\\w+@(\\w+.)+[a-z]{2,3}")
    private String emailaddress;

    /**
     * 投资人传真号码
     */
    @Validator(message = "投资人传真号码", rule = "RE", content = "[0-9]+")
    private String faxno;

    /**
     * 投资人职业代码
     */
    @Validator(message = "投资人职业代码", rule = "RE", content = "[1-9]|[1-2][0-9]|3[0-7]|[99]")
    private String vocationcode;

    /**
     * 投资人住址电话
     */
    @Validator(message = "投资人住址电话", rule = "RE", content = "[0-9]+")
    private String hometelno;

    /**
     * 投资人年收入
     */
    @Validator(message = "投资人年收入", rule = "RE", content = "\\d+\\.\\d+")
    private Integer annualincome;

    /**
     * 投资人手机号码
     */
    @Validator(message = "投资人手机号码", rule = "RE", content = "[0-9]+")
    private String mobiletelno;

    /**
     * 多渠道开户标志
     */
    @Validator(message = "多渠道开户标志", rule = "enum", content = "0,1")
    private Object multiacctflag;

    /**
     * 网点号码
     */
    @Validator(message = "网点号码", rule = "RE", content = "[0-9]+")
    private String branchcode;

    /**
     * 投资人单位电话号码
     */
    @Validator(message = "投资人单位电话号码", rule = "RE", content = "[0-9]+")
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
    @Validator(message = "投资人性别", rule = "enum", content = "1,2")
    private Object sex;

    /**
     * 上交所证券账号
     */
    @Validator(message = "上交所证券账号", rule = "RE", content = "[0-9A-Za-z]+")
    private String shsecuritiesaccountid;

    /**
     * 深交所证券账号
     */
    @Validator(message = "深交所证券账号", rule = "RE", content = "[0-9A-Za-z]+")
    private String szsecuritiesaccountid;

    /**
     * 投资人理财账号
     */
    @Validator(message = "投资人理财账号", rule = "RE", content = "[0-9A-Za-z]+")
    private String taaccountid;

    /**
     * 投资人电话号码
     */
    @Validator(message = "投资人电话号码", rule = "RE", content = "[0-9]+")
    private String telno;

    /**
     * 使用的交易手段
     */
    @Validator(message = "使用的交易手段", rule = "RE", content = "[0-1]+")
    private String tradingmethod;

    /**
     * 未成年人标志
     */
    @Validator(message = "未成年人标志", rule = "enum", content = "0,1")
    private Object minorflag;

    /**
     * 对账单寄送选择
     */
    @Validator(message = "对账单寄送选择", rule = "enum", content = "1,2,3,4,5")
    private Object delivertype;

    /**
     * 经办人识别方式
     */
    @Validator(message = "经办人识别方式", rule = "enum", content = "1,2,3,4")
    private Object transactoridtype;

    /**
     * 理财账户卡的凭证号
     */
    private String accountcardid;

    /**
     * 对账单寄送方式
     */
    @Validator(message = "对账单寄送方式", rule = "RE", content = "[0-1]+")
    private String deliverway;

    /**
     * 投资者国籍
     */
    @Validator(message = "投资者国籍", rule = "RE", content = "[0-9]{3}")
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
    @Validator(message = "证件有效日期", rule = "date")
    private String certvaliddate;

    /**
     * 机构经办人身份证件有效日期
     */
    @Validator(message = "机构经办人身份证件有效日期", rule = "date")
    private String insttrancertvaliddate;

    /**
     * 机构法人身份证件有效日期
     */
    @Validator(message = "机构法人身份证件有效日期", rule = "date")
    private String instreprcertvaliddate;

    /**
     * 客户风险等级
     */
    @Validator(message = "客户风险等级", rule = "enum", content = "1,2,3,4,5")
    private String clientriskrate;

    /**
     * 机构法人经营范围
     */
    @Validator(message = "机构法人经营范围", rule = "RE", content = "[0][1-9]|[1][0-9]|[2][0]")//01-20
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
    @Validator(message = "婚姻状况", rule = "enum", content = "0,1")
    private String marriagestatus;

    /**
     * 家庭人口数
     */
    @Validator(message = "家庭人口数", rule = "RE", content = "[0-9]+")
    private Integer familynum;

    /**
     * 家庭资产
     */
    @Validator(message = "家庭资产", rule = "RE", content = "[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)")
    private BigDecimal penates;

    /**
     * 媒体偏好
     */
    @Validator(message = "媒体偏好", rule = "enum", content = "0,1,2,3,4")
    private String mediahobby;

    /**
     * 投资人英文名
     */
    @Validator(message = "投资人英文名", rule = "RE", content = "[A-Za-z]+")
    private String englishfirstname;

    /**
     * 投资人英文姓
     */
    @Validator(message = "投资人英文姓", rule = "RE", content = "[A-Za-z]+")
    private String englishfamliyname;

    /**
     * 行业（采用国标 GB/T4754-2011）
     */
    @Validator(message = "行业", rule = "RE", content = "[A-T][0-9]+")
    private String vocation;

    /**
     * 企业性质
     */
    @Validator(message = "企业性质", rule = "enum", content = "0,1,2,3")
    private String corpoproperty;

    /**
     * 员工人数
     */
    @Validator(message = "员工人数", rule = "RE", content = "[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)")
    private BigDecimal staffnum;

    /**
     * 兴趣爱好类型
     */
    private String hobbytype;

    /**
     * 省/直辖市
     */
    @Validator(message = "省/直辖市", rule = "RE", content = "[0-9]+")
    private String province;

    /**
     * 市
     */
    @Validator(message = "市", rule = "RE", content = "[0-9]+")
    private String city;

    /**
     * 县/区
     */
    @Validator(message = "县/区", rule = "RE", content = "[0-9]+")
    private String county;

    /**
     * 推荐人
     */
    private String commendperson;

    /**
     * 推荐人类型
     */
    @Validator(message = "推荐人类型", rule = "enum", content = "0,1,2,3,4,5")
    private Object commendpersontype;

    /**
     * 投资人收款银行账户户名
     */
    private String acctnameofinvestorinclearingagency;

    /**
     * 投资人收款银行账户账号
     */
    @Validator(message = "投资人收款银行账户账号", rule = "RE", content = "[0-9]+")
    private String acctnoofinvestorinclearingagency;

    /**
     * 投资人收款银行账户开户行
     */
    private String clearingagency;

    /**
     * 受理方式
     */
    @Validator(message = "受理方式", rule = "enum", content = "0,1,2,3")
    private String acceptmethod;

    /**
     * 冻结原因
     */
    @Validator(message = "冻结原因", rule = "enum", content = "0,1,2,3,4")
    private Object frozencause;

    /**
     * 冻结截止日期（YYYYMMDD）
     */
    @Validator(message = "冻结截止日期", rule = "date")
    private String freezingdeadline;

    /**
     * TA的原确认流水号
     */
    @Validator(message = "TA的原确认流水号", rule = "RE", content = "[0-9A-Za-z]+")
    private String originalserialno;

    /**
     * 原申请单编号
     */
    @Validator(message = "原申请单编号", rule = "RE", content = "[0-9A-Za-z]+")
    private String originalappsheetno;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * 记录状态
     */
    @Validator(message = "记录状态", rule = "enum", content = "0,1,2,3")
    private Object recordstatus;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountApplication();
    }
}