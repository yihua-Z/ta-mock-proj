package com.psbc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * account_application
 * @author 
 */
@Data
public class AccountApplication extends AccountApplicationKey implements DatabaseModel {

    /**
     * 申请单编号
     */
    private String appSheetSerialNo;

    /**
     * TA代码
     */
    private String TACode;

    /**
     * 销售人代码
     */
    private String distributorCode;

    /**
     * 同一交易序列码
     */
    private Integer referenceNo;


    /**
     * 通讯地址
     */
    private String address;

    /**
     * 法人代表身份证件代码
     */
    private String instReprIDCode;

    /**
     * 法人代表证件类型
     */
    private Object instReprIDType;

    /**
     * 法人代表姓名
     */
    private String instReprName;

    /**
     * 个人证件类型及机构证件型
     */
    private Object certificateType;

    /**
     * 投资人证件号码
     */
    private String certificateNo;

    /**
     * 投资人户名
     */
    private String investorName;

    /**
     * 交易发生日期
     */
    private String transactionDate;

    /**
     * 交易发生时间
     */
    private String transactionTime;

    /**
     * 个人/机构标志
     */
    private Object individualOrInstitution;

    /**
     * 机构类型
     */
    private Object institutionType;

    /**
     * 投资人邮政编码
     */
    private String postCode;

    /**
     * 经办人证件号码
     */
    private String transactorCertNo;

    /**
     * 经办人证件类型
     */
    private Object transactorCertType;

    /**
     * 经办人姓名
     */
    private String transactorName;

    /**
     * 投资人理财交易帐号
     */
    private String transactionAccountID;

    /**
     * 业务代码
     */
    private String businessCode;

    /**
     * 对方销售人处投资人基金交易帐号
     */
    private String targetTransactionAccountID;

    /**
     * 投资人收款银行账户账号
     */
    private String acctNoOfFMInClearingAgency;

    /**
     * 投资人收款银行账户户名
     */
    private String acctNameOfFMInClearingAgency;

    /**
     * 理财资金清算机构代码
     */
    private String clearingAgencyCode;

    /**
     * 投资人出生日期
     */
    private String investorsBirthday;

    /**
     * 投资人在销售人处用于交易的资金账号
     */
    private String depositAcct;

    /**
     * 交易所在地区编号
     */
    private String regionCode;

    /**
     * 投资人学历
     */
    private Object educationLevel;

    /**
     * 投资人E-MAIL地址
     */
    private String emailAddress;

    /**
     * 投资人传真号码
     */
    private String faxNo;

    /**
     * 投资人职业代码
     */
    private String vocationCode;

    /**
     * 投资人住址电话
     */
    private String homeTelNo;

    /**
     * 投资人年收入
     */
    private Integer annualIncome;

    /**
     * 投资人手机号码
     */
    private String mobileTelNo;

    /**
     * 多渠道开户标志
     */
    private Object multiAcctFlag;

    /**
     * 网点号码
     */
    private String branchCode;

    /**
     * 投资人单位电话号码
     */
    private String officeTelNo;

    /**
     * 投资人户名简称
     */
    private String accountAbbr;

    /**
     * 密函编号
     */
    private String confidentialDocumentCode;

    /**
     * 投资人性别（1-男，2-女）
     */
    private Object sex;

    /**
     * 上交所证券账号
     */
    private String SHSecuritiesAccountID;

    /**
     * 深交所证券账号
     */
    private String SZSecuritiesAccountID;

    /**
     * 投资人理财账号
     */
    private String TAAccountID;

    /**
     * 投资人电话号码
     */
    private String telNo;

    /**
     * 使用的交易手段
     */
    private String tradingMethod;

    /**
     * 未成年人标志
     */
    private Object minorFlag;

    /**
     * 对账单寄送选择
     */
    private Object deliverType;

    /**
     * 经办人识别方式
     */
    private Object transactorIDType;

    /**
     * 理财账户卡的凭证号
     */
    private String accountCardID;

    /**
     * 对账单寄送方式
     */
    private String deliverWay;

    /**
     * 投资者国籍
     */
    private String nationlity;

    /**
     * 操作（清算）网点编号
     */
    private String netNo;

    /**
     * 客户所属的经纪人
     */
    private String broker;

    /**
     * 工作单位名称
     */
    private String corpName;

    /**
     * 证件有效日期
     */
    private String certValidDate;

    /**
     * 机构经办人身份证件有效日期
     */
    private String instTranCertValidDate;

    /**
     * 机构法人身份证件有效日期
     */
    private String instReprCertValidDate;

    /**
     * 客户风险等级
     */
    private String clientRiskRate;

    /**
     * 机构法人经营范围
     */
    private String instReprManageRange;

    /**
     * 控股股东
     */
    private String controlHolder;

    /**
     * 实际控制人
     */
    private String actualController;

    /**
     * 婚姻状况
     */
    private String marriageStatus;

    /**
     * 家庭人口数
     */
    private Integer familyNum;

    /**
     * 家庭资产
     */
    private BigDecimal penates;

    /**
     * 媒体偏好
     */
    private String mediaHobby;

    /**
     * 投资人英文名
     */
    private String englishFirstName;

    /**
     * 投资人英文姓
     */
    private String englishFamliyName;

    /**
     * 行业（采用国标 GB/T4754-2011）
     */
    private String vocation;

    /**
     * 企业性质
     */
    private String corpoProperty;

    /**
     * 员工人数
     */
    private BigDecimal staffNum;

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
    private String commendPerson;

    /**
     * 推荐人类型
     */
    private Object commendPersonType;

    /**
     * 投资人收款银行账户户名
     */
    private String acctNameOfInvestorInClearingAgency;

    /**
     * 投资人收款银行账户账号
     */
    private String acctNoOfInvestorInClearingAgency;

    /**
     * 投资人收款银行账户开户行
     */
    private String clearingAgency;

    /**
     * 受理方式
     */
    private String acceptMethod;

    /**
     * 冻结原因
     */
    private Object frozenCause;

    /**
     * 冻结截止日期（YYYYMMDD）
     */
    private String freezingDeadline;

    /**
     * TA的原确认流水号
     */
    private String originalSerialNo;

    /**
     * 原申请单编号
     */
    private String originalAppSheetNo;

    /**
     * 摘要/说明
     */
    private String specification;

    /**
     * 记录状态（0-waiting, 1-processing, 2-processed）
     */
    private Object recordStatus;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountApplication();
    }
}