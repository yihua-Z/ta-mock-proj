/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : ta_schema

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 16/11/2021 10:00:50
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_application
-- ----------------------------
DROP TABLE IF EXISTS `account_application`;
create table account_application
(
    AppSheetSerialNo                   varchar(24)                                                                                                             not null comment '申请单编号'
        primary key,
    Address                            varchar(120)                                                                                                            null comment '通讯地址',
    InstReprIDCode                     varchar(30)                                                                                                             null comment '法人代表身份证件代码',
    InstReprIDType                     enum ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'E', 'G', 'H', 'K', 'L')                              null comment '法人代表证件类型',
    InstReprName                       varchar(20)                                                                                                             null comment '法人代表姓名',
    CertificateType                    enum ('0', '1', '2', '3', '4')                                                                                          null comment '个人证件类型及机构证件型',
    CertificateNo                      varchar(30)                                                                                                             null comment '投资人证件号码',
    InvestorName                       varchar(120)                                                                                                            null comment '投资人户名',
    TransactionDate                    char(8)                                                                                                                 not null comment '交易发生日期',
    TransactionTime                    char(6)                                                                                                                 not null comment '交易发生时间',
    IndividualOrInstitution            enum ('0', '1', '2')                                                                                                    null comment '个人/机构标志',
    InstitutionType                    enum ('0', '1', '3', '4', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', '') null comment '机构类型',
    PostCode                           char(6)                                                                                                                 null comment '投资人邮政编码',
    TransactorCertNo                   varchar(30)                                                                                                             null comment '经办人证件号码',
    TransactorCertType                 enum ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B')                                                       null comment '经办人证件类型',
    TransactorName                     varchar(20)                                                                                                             null comment '经办人姓名',
    TransactionAccountID               varchar(17)                                                                                                             not null comment '投资人理财交易帐号',
    DistributorCode                    varchar(9)                                                                                                              not null comment '销售人代码',
    BusinessCode                       char(3)                                                                                                                 not null comment '业务代码',
    TargetTransactionAccountID         varchar(17)                                                                                                             null comment '对方销售人处投资人基金交易帐号',
    AcctNoOfFMInClearingAgency         varchar(28)                                                                                                             null comment '投资人收款银行账户账号',
    AcctNameOfFMInClearingAgency       varchar(60)                                                                                                             null comment '投资人收款银行账户户名',
    ClearingAgencyCode                 varchar(9)                                                                                                              null comment '理财资金清算机构代码',
    InvestorsBirthday                  char(8)                                                                                                                 null comment '投资人出生日期',
    DepositAcct                        varchar(19)                                                                                                             null comment '投资人在销售人处用于交易的资金账号',
    RegionCode                         varchar(4)                                                                                                              null comment '交易所在地区编号',
    EducationLevel                     enum ('01', '02', '03', '04', '05', '06', '07', '08', '09')                                                             null comment '投资人学历',
    EmailAddress                       varchar(40)                                                                                                             null comment '投资人E-MAIL地址',
    FaxNo                              varchar(24)                                                                                                             null comment '投资人传真号码',
    VocationCode                       char(3)                                                                                                                 null comment '投资人职业代码',
    HomeTelNo                          varchar(22)                                                                                                             null comment '投资人住址电话',
    AnnualIncome                       int                                                                                                                     null comment '投资人年收入',
    MobileTelNo                        varchar(24)                                                                                                             null comment '投资人手机号码',
    MultiAcctFlag                      enum ('0', '1')                                                                                                         null comment '多渠道开户标志',
    BranchCode                         varchar(16)                                                                                                             not null comment '网点号码',
    OfficeTelNo                        varchar(22)                                                                                                             null comment '投资人单位电话号码',
    AccountAbbr                        varchar(12)                                                                                                             null comment '投资人户名简称',
    ConfidentialDocumentCode           varchar(8)                                                                                                              null comment '密函编号',
    Sex                                enum ('1', '2')                                                                                                         null comment '投资人性别（1-男，2-女）',
    SHSecuritiesAccountID              varchar(10)                                                                                                             null comment '上交所证券账号',
    SZSecuritiesAccountID              varchar(10)                                                                                                             null comment '深交所证券账号',
    TAAccountID                        varchar(12)                                                                                                             null comment '投资人理财账号',
    TelNo                              varchar(22)                                                                                                             null comment '投资人电话号码',
    TradingMethod                      varchar(8)                                                                                                              null comment '使用的交易手段',
    MinorFlag                          enum ('0', '1')                                                                                                         null comment '未成年人标志',
    DeliverType                        enum ('1', '2', '3', '4', '5')                                                                                          null comment '对账单寄送选择',
    TransactorIDType                   enum ('1', '2', '3', '4')                                                                                               null comment '经办人识别方式',
    AccountCardID                      varchar(8)                                                                                                              null comment '理财账户卡的凭证号',
    DeliverWay                         char(8)                                                                                                                 null comment '对账单寄送方式',
    Nationlity                         char(3)                                                                                                                 null comment '投资者国籍',
    NetNo                              char(9)                                                                                                                 null comment '操作（清算）网点编号',
    Broker                             varchar(12)                                                                                                             null comment '客户所属的经纪人',
    CorpName                           varchar(40)                                                                                                             null comment '工作单位名称',
    CertValidDate                      char(8)                                                                                                                 null comment '证件有效日期',
    InstTranCertValidDate              char(8)                                                                                                                 null comment '机构经办人身份证件有效日期',
    InstReprCertValidDate              char(8)                                                                                                                 null comment '机构法人身份证件有效日期',
    ClientRiskRate                     char                                                                                                                    null comment '客户风险等级',
    InstReprManageRange                varchar(2)                                                                                                              null comment '机构法人经营范围',
    ControlHolder                      varchar(80)                                                                                                             null comment '控股股东',
    ActualController                   varchar(80)                                                                                                             null comment '实际控制人',
    MarriageStatus                     char                                                                                                                    null comment '婚姻状况',
    FamilyNum                          int                                                                                                                     null comment '家庭人口数',
    Penates                            decimal(16, 2)                                                                                                          null comment '家庭资产',
    MediaHobby                         char                                                                                                                    null comment '媒体偏好',
    EnglishFirstName                   varchar(20)                                                                                                             null comment '投资人英文名',
    EnglishFamliyName                  varchar(20)                                                                                                             null comment '投资人英文姓',
    Vocation                           char(4)                                                                                                                 null comment '行业（采用国标 GB/T4754-2011）',
    CorpoProperty                      varchar(2)                                                                                                              null comment '企业性质',
    StaffNum                           decimal(16, 2)                                                                                                          null comment '员工人数',
    Hobbytype                          varchar(2)                                                                                                              null comment '兴趣爱好类型',
    Province                           char(6)                                                                                                                 null comment '省/直辖市',
    City                               char(6)                                                                                                                 null comment '市',
    County                             char(6)                                                                                                                 null comment '县/区',
    CommendPerson                      varchar(40)                                                                                                             null comment '推荐人',
    CommendPersonType                  enum ('0', '1', '2', '3', '4', '5')                                                                                     null comment '推荐人类型',
    AcctNameOfInvestorInClearingAgency varchar(60)                                                                                                             null comment '投资人收款银行账户户名',
    AcctNoOfInvestorInClearingAgency   varchar(28)                                                                                                             null comment '投资人收款银行账户账号',
    ClearingAgency                     varchar(9)                                                                                                              null comment '投资人收款银行账户开户行',
    AcceptMethod                       varchar(1)                                                                                                              null comment '受理方式',
    FrozenCause                        enum ('0', '1', '2', '3', '4')                                                                                          null comment '冻结原因',
    FreezingDeadline                   char(8)                                                                                                                 null comment '冻结截止日期（YYYYMMDD）',
    OriginalSerialNo                   varchar(24)                                                                                                             null comment 'TA的原确认流水号',
    OriginalAppSheetNo                 varchar(24)                                                                                                             null comment '原申请单编号',
    Specification                      varchar(60)                                                                                                             null comment '摘要/说明',
    TACode                             varchar(9)                                                                                                              null comment 'TA代码',
    ReferenceNo                        int                                                                                                                     null comment '同一交易序列码'
)
    charset = utf8;

-- ----------------------------
-- Table structure for account_confirmation
-- ----------------------------
DROP TABLE IF EXISTS `account_confirmation`;
CREATE TABLE `account_confirmation`
(
    `ConfirmID`          int                                                     NOT NULL COMMENT '确认ID',
    `TransactionCfmDate` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL COMMENT '交易确认日期',
    `ReturnCode`         char(4) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL COMMENT '返回码',
    `BusinessCode`       char(3) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL COMMENT '交易码',
    `TransactionDate`    char(8) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL COMMENT '交易日期',
    `TransactionTime`    char(6) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL COMMENT '交易时间',
    `TASerialNo`         int                                                     NULL DEFAULT NULL,
    `FromTAFlag`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `RegionCode`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `NetNo`              int                                                     NULL DEFAULT NULL,
    `Specification`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `CustomerNo`         int                                                     NULL DEFAULT NULL,
    `ErrorDetail`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ConfirmID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_expectation
-- ----------------------------
DROP TABLE IF EXISTS `account_expectation`;
CREATE TABLE `account_expectation`
(
    `AppSheetSerialNo`   varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请单编号',
    `ReturnCode`         varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易处理返回代码',
    `ErrorDetail`        varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出错详细信息',
    `Status`             varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '条目状态',
    `TransactionCfmDate` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    `TASerialNO`         varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'TA确认交易流水号',
    `FromTAFlag`         varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否注册登记人发起业务标志',
    `RegionCode`         varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易所在地区编号',
    `NetNo`              varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '操作（清算）网点编号',
    `Specification`      varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要/说明',
    PRIMARY KEY (`AppSheetSerialNo`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`
(
    `TransactionAccountID` int NOT NULL,
    `TAAcountID`           int NOT NULL,
    PRIMARY KEY (`TransactionAccountID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acct_reconciliation
-- ----------------------------
DROP TABLE IF EXISTS `acct_reconciliation`;
CREATE TABLE `acct_reconciliation`
(
    `ReconciliationID`               int                                                    NOT NULL AUTO_INCREMENT COMMENT '分红ID',
    `AvailableVol`                   decimal(16, 2)                                         NOT NULL COMMENT '持有人可用理财产品份数',
    `TotalVolOfDistributorInTA`      decimal(16, 2)                                         NOT NULL COMMENT '理财产品总份数（含冻结）',
    `TransactionCfmDate`             varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    `TotalFrozenVol`                 decimal(16, 2)                                         NOT NULL COMMENT '理财产品冻结总份数',
    `TASerialNO`                     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'TA确认交易流水号',
    `TotalBackendLoad`               decimal(16, 2)                                         NOT NULL COMMENT '交易后端收费总额',
    `ShareClass`                     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '收费方式',
    `DetailFlag`                     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '明细标志',
    `ShareRegisterDate`              varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额注册日期',
    `UndistributeMonetaryIncome`     decimal(16, 2)                                         NOT NULL COMMENT '货币式理财未付收益金额',
    `UndistributeMonetaryIncomeFlag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '货币式理财未付收益金额正负',
    `GuaranteedAmount`               decimal(16, 2)                                         NOT NULL COMMENT '剩余保本金额',
    `SourceType`                     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额原始来源',
    `FundCode`                       varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品代码',
    `TAAccountID`                    varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投资人理财账号',
    `TACode`                         varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `DistributorCode`                varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    PRIMARY KEY (`ReconciliationID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acct_share
-- ----------------------------
DROP TABLE IF EXISTS `acct_share`;
CREATE TABLE `acct_share`
(
    `TAAccountID`               varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投资人理财账号',
    `DistributorCode`           varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    `TACode`                    varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `TotalVolOfDistributorInTA` decimal(16, 2)                                         NOT NULL COMMENT '理财产品总份数（含冻结）',
    `TotalFrozenVol`            decimal(16, 2)                                         NOT NULL COMMENT '理财产品冻结总份数',
    `TransactionCfmDate`        varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    PRIMARY KEY (`TAAccountID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`
(
    `TAAccountID`     int                                                       NOT NULL COMMENT '投资人理财帐号',
    `Name`            varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '投资人户名',
    `Sex`             enum ('1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投资人性别；1-男，2-女',
    `Nationality`     char(3) CHARACTER SET utf8 COLLATE utf8_general_ci        NULL DEFAULT NULL COMMENT '投资人国籍（采用GB/T 2659-2000）',
    `Vocation`        char(4) CHARACTER SET utf8 COLLATE utf8_general_ci        NULL DEFAULT NULL COMMENT '投资人行业（采用国标 GB/T4754-2011）',
    `Address`         varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '投资人通讯地址',
    `TelNo`           varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '投资人手机号码',
    `CertificateType` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL COMMENT '个人证件类型及机构证件类型',
    `CertificateNo`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '投资人证件号码',
    `CertValidDate`   char(8) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL COMMENT '证件有效期',
    `DistributorCode` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '销售人代码',
    `BranchCode`      varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '网点号码',
    `TACode`          varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT 'TA代码',
    `CustomerNo`      varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT 'TA客户编号',
    PRIMARY KEY (`TAAccountID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dividend
-- ----------------------------
DROP TABLE IF EXISTS `dividend`;
CREATE TABLE `dividend`
(
    `DividendID`                   int                                                    NOT NULL AUTO_INCREMENT COMMENT '分红ID',
    `BasisforCalculatingDividend`  decimal(16, 2)                                         NOT NULL COMMENT '红利/红利再投资基数',
    `VolOfDividendforReinvestment` decimal(16, 2)                                         NOT NULL COMMENT '理财账户红利再投资理财产品份数',
    `DividentDate`                 varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '分红日/发放日',
    `DividendAmount`               decimal(16, 2)                                         NOT NULL COMMENT '理财账户红利资金',
    `ConfirmedAmount`              decimal(16, 2)                                         NOT NULL COMMENT '每笔交易确认金额',
    `ReturnCode`                   varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易处理返回代码',
    `TransactionAccountID`         varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投资人理财交易账号',
    `DistributorCode`              varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    `BusinessCode`                 varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '业务代码',
    `TAAccountID`                  varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投资人理财账号',
    `DividendPerUnit`              decimal(16, 2)                                         NOT NULL COMMENT '单位理财产品分红金额（含税）',
    `DepositAcct`                  varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投资人在销售人处用于交易的资金账号',
    `RegionCode`                   varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易所在地区编号',
    `DownLoaddate`                 varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易数据下传日期',
    `Charge`                       decimal(10, 2)                                         NOT NULL COMMENT '手续费',
    `AgencyFee`                    decimal(10, 2)                                         NOT NULL COMMENT '代理费',
    `TotalFrozenVol`               decimal(16, 2)                                         NOT NULL COMMENT '理财产品冻结总份数',
    `BranchCode`                   varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网点号码',
    `OtherFee1`                    decimal(10, 2)                                         NOT NULL COMMENT '其他费用1',
    `OtherFee2`                    decimal(16, 2)                                         NOT NULL COMMENT '其他费用2',
    `IndividualOrInstitution`      varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '个人/机构标志',
    `DividendRatio`                decimal(16, 2)                                         NOT NULL COMMENT '红利比例',
    `StampDuty`                    decimal(16, 2)                                         NOT NULL COMMENT '印花税',
    `FrozenBalance`                decimal(16, 2)                                         NOT NULL COMMENT '冻结金额',
    `TransferFee`                  decimal(10, 2)                                         NOT NULL COMMENT '过户费',
    `ShareClass`                   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '收费方式',
    `FeeCalculator`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '计费人',
    `DrawBonusUnit`                decimal(10, 0)                                         NOT NULL COMMENT '分红单位',
    `FrozenSharesforReinvest`      decimal(16, 2)                                         NOT NULL COMMENT '冻结再投资份额',
    `DividendType`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '分红类型',
    `OriginalAppSheetNo`           varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原申请单编号',
    `AchievementPay`               decimal(16, 2)                                         NOT NULL COMMENT '业绩报酬',
    `AchievementCompen`            decimal(16, 2)                                         NOT NULL COMMENT '业绩补偿',
    `TACode`                       varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `TransactionCfmDate`           varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    `FundCode`                     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品代码',
    PRIMARY KEY (`DividendID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exception
-- ----------------------------
DROP TABLE IF EXISTS `exception`;
CREATE TABLE `exception`
(
    `AppSheetSerialNo`     varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '申请单编号',
    `TACode`               varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT 'TA代码',
    `DistributorCode`      varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '销售人代码',
    `ReferenceNumber`      int                                                       NOT NULL COMMENT '同一记录序列号',
    `AppTransactionDate`   char(8) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL COMMENT '交易日期',
    `TransactionAccountID` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '投资人理财交易账号',
    `BusinessCode`         char(3) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL COMMENT '业务代码',
    `ErrorType`            enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '错误类型（0-非业务逻辑错误，1-业务逻辑错误）',
    `Speification`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '错误说明',
    PRIMARY KEY (`AppSheetSerialNo`, `TACode`, `DistributorCode`, `ReferenceNumber`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fund_date
-- ----------------------------
DROP TABLE IF EXISTS `fund_date`;
CREATE TABLE `fund_date`
(
    `TACode`             varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `FundCode`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品代码',
    `DistributorCode`    varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    `DateType`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '日期类型',
    `CorrCfmDate`        decimal(8, 0)                                          NOT NULL COMMENT '对应确认日',
    `TransactionCfmDate` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    PRIMARY KEY (`TACode`, `FundCode`, `CorrCfmDate`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fund_info
-- ----------------------------
DROP TABLE IF EXISTS `fund_info`;
CREATE TABLE `fund_info`
(
    `FundCode`               varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品代码',
    `FundName`               varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品名称',
    `TotalFundVol`           decimal(16, 2)                                         NOT NULL COMMENT '理财产品总份数',
    `FundStatus`             varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品状态',
    `NAV`                    decimal(9, 6)                                          NOT NULL COMMENT '单位净值',
    `UpdateDate`             varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品净值日期',
    `NetValueType`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '净值类型',
    `AccumulativeNAV`        decimal(9, 6)                                          NOT NULL COMMENT '累计单位净值',
    `ConvertStatus`          varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品转换状态',
    `PeriodicStatus`         varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '定期定额状态',
    `TransferAgencyStatus`   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '转托管状态',
    `FundSize`               decimal(16, 2)                                         NOT NULL COMMENT '理财产品规模',
    `CurrencyType`           varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '结算币种',
    `AnnouncFlag`            varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '公告标志',
    `DefDividendMethod`      varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '默认分红方式',
    `InstAppSubsAmnt`        decimal(16, 2)                                         NOT NULL COMMENT '法人追加认购金额',
    `InstAppSubsVol`         decimal(16, 2)                                         NOT NULL COMMENT '法人追加认购份数',
    `MinAmountByInst`        decimal(16, 2)                                         NOT NULL COMMENT '法人首次认购最低金额',
    `MinVolByInst`           decimal(16, 2)                                         NOT NULL COMMENT '法人首次认购最低份数',
    `CustodianCode`          varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '托管人代码',
    `AmountOfPeriodicSubs`   decimal(16, 2)                                         NOT NULL COMMENT '定时定额申购的金额',
    `DateOfPeriodicSubs`     varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '定时定额申购日期',
    `MaxRedemptionVol`       decimal(16, 2)                                         NOT NULL COMMENT '理财最高赎回份数',
    `MinAccountBalance`      decimal(16, 2)                                         NOT NULL COMMENT '理财最低持有份数',
    `IPOStartDate`           varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品募集开始日期',
    `IPOEndDate`             varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品募集结束日期',
    `FundManagerCode`        varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品总份数',
    `IndiAppSubsVol`         decimal(16, 2)                                         NOT NULL COMMENT '个人追加认购份数',
    `IndiAppSubsAmount`      decimal(16, 2)                                         NOT NULL COMMENT '个人追加认购金额',
    `MinSubsVolByIndi`       decimal(16, 2)                                         NOT NULL COMMENT '个人首次认购最低份数',
    `MinSubsAmountByIndi`    decimal(16, 2)                                         NOT NULL COMMENT '个人首次认购最低金额',
    `RegistrarCode`          varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '注册登记人代码',
    `FundSponsor`            varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财发起人',
    `TradingPrice`           decimal(7, 4)                                          NOT NULL COMMENT '交易价格',
    `FaceValue`              decimal(7, 4)                                          NOT NULL COMMENT '理财产品面值',
    `DividentDate`           varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '分红日/发放日',
    `RegistrationDate`       varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '权益登记日期',
    `XRDate`                 varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '除权日',
    `MaxSubsVolByIndi`       decimal(16, 2)                                         NOT NULL COMMENT '个人最高认购份数',
    `MaxSubsAmountByIndi`    decimal(16, 2)                                         NOT NULL COMMENT '个人最高认购金额',
    `MaxSubsVolByInst`       decimal(16, 2)                                         NOT NULL COMMENT '法人最高认购份数',
    `MaxSubsAmountByInst`    decimal(16, 2)                                         NOT NULL COMMENT '法人最高认购金额',
    `UnitSubsVolByIndi`      decimal(16, 2)                                         NOT NULL COMMENT '个人认购份数单位',
    `UnitSubsAmountByIndi`   decimal(16, 2)                                         NOT NULL COMMENT '个人认购金额单位',
    `UnitSubsVolByInst`      decimal(16, 2)                                         NOT NULL COMMENT '法人认购份数单位',
    `UnitSubsAmountByInst`   decimal(16, 2)                                         NOT NULL COMMENT '法人认购金额单位',
    `MinBidsAmountByIndi`    decimal(16, 2)                                         NOT NULL COMMENT '个人首次申购最低金额',
    `MinBidsAmountByInst`    decimal(16, 2)                                         NOT NULL COMMENT '法人首次申购最低金额',
    `MinAppBidsAmountByIndi` decimal(16, 2)                                         NOT NULL COMMENT '个人追加申购最低金额',
    `MinAppBidsAmountByInst` decimal(16, 2)                                         NOT NULL COMMENT '法人追加申购最低金额',
    `MinRedemptionVol`       decimal(16, 2)                                         NOT NULL COMMENT '理财最少赎回份数',
    `MinInterconvertVol`     decimal(16, 2)                                         NOT NULL COMMENT '最低理财产品转换份数',
    `IssueTypeByIndi`        varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '个人发行方式',
    `IssueTypeByInst`        varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '机构发行方式',
    `SubsType`               varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '认购方式',
    `CollectFeeType`         varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易费收取方式',
    `NextTradeDate`          varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '下一开放日',
    `ValueLine`              decimal(7, 2)                                          NOT NULL COMMENT '产品价值线数值',
    `TotalDivident`          decimal(8, 5)                                          NOT NULL COMMENT '累积单位分红',
    `FundIncome`             decimal(8, 5)                                          NOT NULL COMMENT '货币式理财万份收益率',
    `FundIncomeFlag`         varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '货币式理财万份收益正',
    `Yield`                  decimal(8, 5)                                          NOT NULL COMMENT '货币式理财七日年收益',
    `YieldFlag`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '货币式理财七日年收益正负',
    `GuaranteedNAV`          decimal(9, 6)                                          NOT NULL COMMENT '保本净值',
    `FundYearIncomeRate`     decimal(8, 5)                                          NOT NULL COMMENT '货币式理财年收益率',
    `FundYearIncomeRateFlag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '货币式理财年收益率正负',
    `IndiMaxPurchase`        decimal(16, 2)                                         NOT NULL COMMENT '个人最大申购金额',
    `InstMaxPurchase`        decimal(16, 2)                                         NOT NULL COMMENT '法人最大申购金额',
    `IndiDayMaxSumBuy`       decimal(16, 2)                                         NOT NULL COMMENT '个人当日累计购买最大金额',
    `InstDayMaxSumBuy`       decimal(16, 2)                                         NOT NULL COMMENT '法人当日累计购买最大金额',
    `IndiDayMaxSumRedeem`    decimal(16, 2)                                         NOT NULL COMMENT '个人当日累计赎回最大份额',
    `InstDayMaxSumRedeem`    decimal(16, 2)                                         NOT NULL COMMENT '法人当日累计赎回最大份额',
    `IndiMaxRedeem`          decimal(16, 2)                                         NOT NULL COMMENT '个人最大赎回份额',
    `InstMaxRedeem`          decimal(16, 2)                                         NOT NULL COMMENT '法人最大赎回份额',
    `FundDayIncomeFlag`      varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品当日总收益正负',
    `FundDayIncome`          decimal(16, 2)                                         NOT NULL COMMENT '理财产品当日总收益',
    `AllowBreachRedempt`     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '允许违约赎回标志',
    `FundType`               varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品类型',
    `FundTypeName`           varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品类型名称',
    `RegistrarName`          varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册登记人名称',
    `FundManagerName`        varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品总份数名称',
    `FundServerTel`          varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资管公司客服电话',
    `FundInternetAddress`    varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资管公司网站网址',
    `TACode`                 varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `DistributorCode`        varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    PRIMARY KEY (`FundCode`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fund_para_config
-- ----------------------------
DROP TABLE IF EXISTS `fund_para_config`;
CREATE TABLE `fund_para_config`
(
    `FundParaID`                 int                                                    NOT NULL AUTO_INCREMENT COMMENT '产品参数ID',
    `DistributorCode`            varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    `FundCode`                   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品代码',
    `DownLoaddate`               varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易数据下传日期',
    `PrdRiskRate`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品风险等级',
    `DebtFundType`               varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '中债产品划分',
    `AllowClientGroup`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '允许销售的中债客户组别',
    `FinancialRegistrationCode`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财登记编码',
    `SaleObject`                 varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售对象（发售对象）',
    `IsCycleFund`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否周期产品',
    `CycleDays`                  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '周期天数',
    `CycleNums`                  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '最长周期数',
    `DefCycleNums`               varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '默认期数',
    `BreachOfContractFee`        decimal(9, 8)                                          NOT NULL COMMENT '违约赎回费率',
    `LCFundType`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品类型',
    `IPOType`                    varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品募集方式',
    `InvestmentTargets`          varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '投资标的（投资性质）',
    `ClearDays`                  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '结算天数',
    `RedeemAmtDays`              varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '赎回资金到账天数（结算天数）',
    `DivAmtDays`                 varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '分红资金到账天数（结算天数）',
    `FundEndDays`                varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品到期资金到账天数结算天数',
    `FundIssuanceFailDays`       varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '发行失败/比例退款天数',
    `CashFlag`                   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '钞汇标志',
    `IncomeEndDate`              varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品收益到期日',
    `IncomeCurrType`             varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品收益币种',
    `CostCurrType`               varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '本金返还币种',
    `ChoiceBonusType`            varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '可选分红方式',
    `CancelOrderType`            varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '购买（认购）撤单',
    `BuyUpperLimitday`           decimal(16, 2)                                         NOT NULL COMMENT '单日申购上限',
    `IsPartRedeem`               varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '允许部分赎回',
    `LargeRedeemRatio`           decimal(9, 8)                                          NOT NULL COMMENT '巨额赎回比例',
    `RedeemAmtReturn`            varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '赎回本金返还方式',
    `FundMinBala`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品最低募集额',
    `FundMaXBala`                decimal(16, 2)                                         NOT NULL COMMENT '产品最高募集额',
    `Subscribemode`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '认购帐务模式',
    `Purchasemode`               varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '申购帐务模式',
    `RealRedeemRatio`            decimal(5, 4)                                          NOT NULL COMMENT '实时赎回资金比例',
    `RealRedeemUpperLimitOfDay`  decimal(16, 2)                                         NOT NULL COMMENT '当日实时赎回份额上限',
    `RealRedeemUpperLimitOfSolo` decimal(16, 2)                                         NOT NULL COMMENT '单人单日快速赎回上限',
    `ShareDoMethod`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额计算方式',
    `OrgRedeemBase`              decimal(16, 2)                                         NOT NULL COMMENT '对公赎回基数',
    `PerRedeemBase`              decimal(16, 2)                                         NOT NULL COMMENT '对私赎回基数',
    `ComparisonRatio`            decimal(9, 8)                                          NOT NULL COMMENT '业绩基准',
    `FundIssuer`                 varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品发行人',
    `ManagementBasic`            varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '浮动管理费年化基数',
    `ExcessiveRtio`              decimal(9, 8)                                          NOT NULL COMMENT '超额分层比例',
    `SubBasic`                   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '认购利息年化基础',
    `SubDelayedDays`             varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '认购计息天数',
    `FundCollectAccount`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品募集账户',
    `ExcessiveSubRtio`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '超额认购比例确认',
    `InterestRate`               decimal(9, 8)                                          NOT NULL COMMENT '认购利息利率',
    `OpenTime`                   varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '开市时间',
    `CloseTime`                  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '闭市时间',
    `ShareClass`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '收费方式',
    `FundNameAbbr`               varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '理财产品简称',
    `FinancType`                 varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品类型',
    `IsGuaranteedFund`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否保本理财',
    `IsLOFFund`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否LOF',
    `IsQDIIFund`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否QDII',
    `IsETFFund`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否ETF',
    `LxRedeemFeeBackRatio`       decimal(5, 4)                                          NOT NULL COMMENT '赎回费归理财资产比例',
    `RedemptionSequence`         varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '指定赎回方式',
    `BuyUpperAmount`             decimal(16, 2)                                         NOT NULL COMMENT '申购金额上限',
    `CovertInUpperAmount`        decimal(16, 2)                                         NOT NULL COMMENT '理财产品转换转入金额上限',
    `PeriodSubUpperAmount`       decimal(16, 2)                                         NOT NULL COMMENT '定时定额申购金额上限',
    `FundEstablishDate`          varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '理财产品成立日期',
    `ExchangeFlag`               varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易所标志',
    `Specification`              varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要/说明',
    `WholeFlag`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '全量标志',
    `ModifyWay`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '修改方式',
    `OperateDate`                varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '生效日期',
    `IssPrice`                   decimal(18, 8)                                         NOT NULL COMMENT '发行价格',
    `IncomeDate`                 varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品起息日期',
    `OMinAccountBalance`         decimal(17, 2)                                         NOT NULL COMMENT '机构最低持有份额',
    `PMinRedemptionVol`          decimal(17, 2)                                         NOT NULL COMMENT '个人单笔最少赎回份额',
    `PMinInterconvertVol`        decimal(17, 2)                                         NOT NULL COMMENT '个人最低产品转换份额',
    `OMinRedemptionVol`          decimal(17, 2)                                         NOT NULL COMMENT '机构单笔最少赎回份额',
    `OMinInterconvertVol`        decimal(17, 2)                                         NOT NULL COMMENT '机构最低产品转换份额',
    `SubcancelFlag`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '认购隔日撤单标志',
    `InterestWay`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品收益方式',
    `PrdProfitKind`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '收益特征',
    `IsAllowPrdRealTimeRansom`   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否允许实时赎回',
    `BaseDays`                   varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品计息基数',
    `TransWay`                   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易方式',
    `ParentsPrdCode`             varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应资产池代码',
    `PrdTemplateCode`            varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '产品模板号',
    `AlimitEndDate`              varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '封闭到期日',
    `ORedUnit`                   decimal(10, 2)                                         NOT NULL COMMENT '机构赎回单位',
    `OMaxAccuAmt`                decimal(17, 2)                                         NOT NULL COMMENT '机构单户累计最大购买金额',
    `PRedUnit`                   decimal(10, 2)                                         NOT NULL COMMENT '个人赎回单位',
    `PMaxAccuAmt`                decimal(17, 2)                                         NOT NULL COMMENT '个人单户累计最大购买金额',
    `ModelCOMMENT`               varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '预期收益率说明/业绩比较基准',
    `DayBeforeCfm`               varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '周期清算前N天允许交易',
    `BuyIsControlConfirmFlag`    varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '购买确认起息天数',
    `RedeemIsControlConfirmFlag` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '赎回确认延迟天数',
    `ZeroSetUpFlag`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否零认购成立',
    `LimitFlag`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '额度控制标志',
    `AgioType`                   varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '折扣率方式',
    `SellerLimit`                decimal(17, 2)                                         NOT NULL COMMENT '销售商最大限额',
    `LockDays`                   decimal(4, 0)                                          NOT NULL COMMENT '锁定期天数',
    `IsCheckInvester`            varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否校验合格投资者',
    `ClientCycleMode`            varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '客户周期模式',
    `MinCycleDays`               decimal(4, 0)                                          NOT NULL COMMENT '最小周期天数',
    `MaxCyleDays`                decimal(4, 0)                                          NOT NULL COMMENT '最大周期天数',
    `CycleAllottedDays`          varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '周期期限集合',
    `FundBuyAccount`             varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品申购款过渡户',
    `TACode`                     varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    PRIMARY KEY (`FundParaID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for prepayment_acct
-- ----------------------------
DROP TABLE IF EXISTS `prepayment_acct`;
CREATE TABLE `prepayment_acct`
(
    `TACode`                       varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `OriginalDistributorCode`      varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '源销售人代码',
    `TargetDistributorCode`        varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '目标销售人代码',
    `OriginalTransactionAccountID` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源理财交易账号',
    `TargetTransactionAccountID`   varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标理财交易账号',
    `OriginalRegionCode`           varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '源地区编号',
    `TargetRegionCode`             varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '目标地区编号',
    `OriginalBranchCode`           varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源网点号',
    `TargetBranchCode`             varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标网点号',
    `TotalVol`                     decimal(16, 2)                                         NOT NULL COMMENT '过户份额',
    PRIMARY KEY (`TACode`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ta_business_config
-- ----------------------------
DROP TABLE IF EXISTS `ta_business_config`;
CREATE TABLE `ta_business_config`
(
    `TACode`              varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT 'TA代码',
    `Direction`           enum ('1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务方向（‘1’-单向，‘2’-双向）',
    `ApplyBusinessCode`   char(3) CHARACTER SET utf8 COLLATE utf8_general_ci        NULL DEFAULT NULL COMMENT '申请业务代码',
    `SettlementDays`      int                                                       NOT NULL COMMENT '延迟处理天数',
    `ProcessStatus`       enum ('1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理状态（‘1’-成功，‘2’-失败）',
    `ConfimBusinessCodes` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '确认业务代码(可以是多个，以逗号分隔)',
    PRIMARY KEY (`TACode`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for timed_application
-- ----------------------------
DROP TABLE IF EXISTS `timed_application`;
CREATE TABLE `timed_application`
(
    `AppSheetSerialNo` int                                                       NOT NULL AUTO_INCREMENT COMMENT '定时任务ID',
    `TACode`           varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '任务名字（即包名+类名+方法名）',
    `DistributorCode`  varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '销售人代码',
    `TransactionDate`  char(8) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL COMMENT '处理日期',
    `Completness`      enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否完成标志（每日由脚本完成更新）',
    PRIMARY KEY (`AppSheetSerialNo`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for timed_task
-- ----------------------------
DROP TABLE IF EXISTS `timed_task`;
CREATE TABLE `timed_task`
(
    `TaskID`      int                                                       NOT NULL AUTO_INCREMENT COMMENT '定时任务ID',
    `TaskName`    varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '任务名字（即包名+类名+方法名）',
    `TaskTime`    char(6) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL COMMENT '任务时间（最早时间，HHMMSS）',
    `Necessity`   enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否必须',
    `Dependency`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '前置任务ID（可以为多个）',
    `Completness` enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否完成标志（每日由脚本完成更新）',
    PRIMARY KEY (`TaskID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_application
-- ----------------------------
DROP TABLE IF EXISTS `transaction_application`;
CREATE TABLE `transaction_application`
(
    `AppSheetSerialNo`           varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci                NOT NULL COMMENT '申请单编号',
    `FundCode`                   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '理财产品代码',
    `LargeRedemptionFlag`        enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NULL DEFAULT NULL COMMENT '巨额赎回处理标志(0-取消，1-顺延)',
    `TransactionDate`            char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NOT NULL COMMENT '交易发生日期(YYYYMMDD)',
    `TransactionTime`            char(6) CHARACTER SET utf8 COLLATE utf8_general_ci                    NOT NULL COMMENT '交易发生时间(HHMMSS)',
    `TransactionAccountID`       varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci                NOT NULL COMMENT '投资人在销售机构内开设的用于交易的账号',
    `DistributorCode`            varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci                 NOT NULL COMMENT '销售人代码',
    `ApplicationVol`             decimal(16, 2)                                                        NULL DEFAULT NULL COMMENT '申请理财产品份数',
    `ApplicationAmount`          decimal(16, 2)                                                        NULL DEFAULT NULL COMMENT '申请金额',
    `BusinessCode`               char(3) CHARACTER SET utf8 COLLATE utf8_general_ci                    NOT NULL COMMENT '业务代码',
    `TAAccountID`                varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci                NOT NULL COMMENT '投资人理财帐号',
    `DiscountRateOfCommission`   decimal(5, 4)                                                         NULL DEFAULT NULL COMMENT '销售佣金折扣率',
    `DepositAcct`                varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '投资人在销售人处用于交易的资金账号',
    `RegionCode`                 varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '交易所在地区编号',
    `CurrencyType`               char(3) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '结算币种',
    `BranchCode`                 varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '网点号码',
    `OriginalAppSheetNo`         int                                                                   NULL DEFAULT NULL COMMENT '原申请单编号',
    `OriginalSubsDate`           char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '原申购日期',
    `IndividualOrInstitution`    enum ('1','0','2') CHARACTER SET utf8 COLLATE utf8_general_ci         NULL DEFAULT NULL COMMENT '个人/机构标志(0-机构，1-个人，2-产品)',
    `ValidPeriod`                int                                                                   NULL DEFAULT NULL COMMENT '交易申请有效天数',
    `DaysRedemptionInAdvance`    int                                                                   NULL DEFAULT NULL COMMENT '预约赎回工作日天数',
    `RedemptionDateInAdvance`    char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '预约赎回日期(客户周期产品，购买时上送该字段作为指定到期日) ',
    `OriginalSerialNo`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT 'TA原确认流水号',
    `DateOfPeriodicSubs`         char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '定期定额申购日期',
    `TASerialNO`                 varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT 'TA确认交易流水号',
    `TermOfPeriodicSubs`         int                                                                   NULL DEFAULT NULL COMMENT '定期定额申购期限',
    `FutureBuyDate`              char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '指定申购日期',
    `TargetDistributorCode`      varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '对方销售人代码',
    `Charge`                     decimal(10, 2)                                                        NULL DEFAULT NULL COMMENT '手续费',
    `TargetBranchCode`           varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '对方网点号',
    `TargetTransactionAccountID` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '对方销售人处投资人理财交易账号',
    `TargetRegionCode`           varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '对方所在地区编号',
    `DividendRatio`              decimal(16, 2)                                                        NULL DEFAULT NULL COMMENT '红利比例',
    `Specification`              varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '摘要/说明',
    `CodeOfTargetFund`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '转换时的目标理财产品代码',
    `TotalBackendLoad`           decimal(16, 2)                                                        NULL DEFAULT NULL COMMENT '交易后端收费总额',
    `ShareClass`                 enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NOT NULL COMMENT '收费方式',
    `OriginalCfmDate`            char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT 'TA的原确认日期',
    `DetailFlag`                 enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NULL DEFAULT NULL COMMENT '明细标志',
    `OriginalAppDate`            char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '原申请日期',
    `DefDividendMethod`          enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NULL DEFAULT NULL COMMENT '默认分红方式',
    `FrozenCause`                enum ('0','1','2','3','4') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冻结原因(0-司法冻结，1-柜台冻结 \r\n2-质押冻结，3-质押、司法双重冻结 4-柜台、司法双重冻结\r\n)',
    `FreezingDeadline`           char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '冻结截止日期',
    `VarietyCodeOfPeriodicSubs`  varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '定时定额品种代码',
    `SerialNoOfPeriodicSubs`     varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '定时定额申购序号',
    `RationType`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '定期定额种类',
    `TargetTAAccountID`          varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '对方理财账号',
    `TargetRegistrarCode`        varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '对方TA代码',
    `NetNo`                      varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '操作（清算）网点编号',
    `CustomerNo`                 varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT 'TA客户编号',
    `TargetShareType`            enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NULL DEFAULT NULL COMMENT '对方理财产品份额类别(0-前收费，1-后收费)',
    `RationProtocolNo`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '定期定额协议号',
    `BeginDateOfPeriodicSubs`    char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '定时定额申购起始日期',
    `EndDateOfPeriodicSubs`      char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '定时定额申购终止日期',
    `SendDayOfPeriodicSubs`      int                                                                   NULL DEFAULT NULL COMMENT '定时定额申购每月发送日',
    `Broker`                     varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '经纪人',
    `SalesPromotion`             varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '促销活动代码',
    `AcceptMethod`               char(1) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '受理方式',
    `ForceRedemptionType`        enum ('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci         NULL DEFAULT NULL COMMENT '强制赎回类型(0-强制赎回，1-违约赎回，2-到期)',
    `TakeIncomeFlag`             enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NULL DEFAULT NULL COMMENT '带走收益标志(0-不带走，1-带走)',
    `PurposeOfPeSubs`            varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci                NULL DEFAULT NULL COMMENT '定投目的',
    `FrequencyOfPeSubs`          int                                                                   NULL DEFAULT NULL COMMENT '定投频率',
    `PeriodSubTimeUnit`          char(1) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '定投周期单位',
    `BatchNumOfPeSubs`           decimal(16, 2)                                                        NULL DEFAULT NULL COMMENT '定投期数',
    `CapitalMode`                char(2) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '资金方式',
    `DetailCapticalMode`         char(2) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '明细资金方式',
    `BackenloadDiscount`         decimal(5, 4)                                                         NULL DEFAULT NULL COMMENT '补差费折扣率',
    `CombineNum`                 varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '组合编号',
    `FutureSubscribeDate`        char(8) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '指定认购日期',
    `TradingMethod`              varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci                 NULL DEFAULT NULL COMMENT '使用的交易手段',
    `LargeBuyFlag`               enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci             NULL DEFAULT NULL COMMENT '巨额购买处理标志',
    `ChargeType`                 char(1) CHARACTER SET utf8 COLLATE utf8_general_ci                    NULL DEFAULT NULL COMMENT '收费类型',
    `SpecifyRateFee`             decimal(9, 8)                                                         NULL DEFAULT NULL COMMENT '指定费率',
    `SpecifyFee`                 decimal(16, 2)                                                        NULL DEFAULT NULL COMMENT '指定费用',
    `TACode`                     varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci                 NOT NULL COMMENT 'TA代码',
    `ReferenceNumber`            int                                                                   NOT NULL COMMENT '同一申请序列号',
    PRIMARY KEY (`AppSheetSerialNo`, `DistributorCode`, `ReferenceNumber`, `TACode`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_confirmation
-- ----------------------------
DROP TABLE IF EXISTS `transaction_confirmation`;
CREATE TABLE `transaction_confirmation`
(
    `ConfirmID`                         int                                                    NOT NULL AUTO_INCREMENT COMMENT '确认ID',
    `TransactionCfmDate`                varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    `ConfirmedVol`                      decimal(16, 2)                                         NOT NULL COMMENT '理财账户交易确认份数',
    `ConfirmedAmount`                   decimal(16, 2)                                         NOT NULL COMMENT '每笔交易确认金额',
    `TransactionDate`                   varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易发生日期',
    `TransactionTime`                   varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易发生时间',
    `ReturnCode`                        varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易处理返回代码',
    `BusinessCode`                      varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '业务代码',
    `BusinessFinishFlag`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '业务过程完全结束标识',
    `RegionCode`                        varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易所在地区编号',
    `DownLoaddate`                      varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易数据下传日期',
    `AgencyFee`                         decimal(10, 2)                                         NOT NULL COMMENT '代理费',
    `NAV`                               decimal(9, 6)                                          NOT NULL COMMENT '单位净值',
    `OtherFee1`                         decimal(10, 2)                                         NOT NULL COMMENT '其他费用1',
    `StampDuty`                         decimal(16, 2)                                         NOT NULL COMMENT '印花税',
    `RateFee`                           decimal(9, 8)                                          NOT NULL COMMENT '费率',
    `Specification`                     varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要/说明',
    `TransferDirection`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '转入/转出标识',
    `Interest`                          decimal(10, 2)                                         NOT NULL COMMENT '理财账户利息金额',
    `VolumeByInterest`                  decimal(16, 2)                                         NOT NULL COMMENT '利息产生的理财产品份数',
    `InterestTax`                       decimal(16, 2)                                         NOT NULL COMMENT '利息税',
    `TradingPrice`                      decimal(7, 4)                                          NOT NULL COMMENT '交易价格',
    `Tax`                               decimal(16, 2)                                         NOT NULL COMMENT '税金',
    `TargetNAV`                         decimal(9, 6)                                          NOT NULL COMMENT '目标理财产品的单位净值',
    `TargetFundPrice`                   decimal(7, 4)                                          NOT NULL COMMENT '目标理财产品的价格',
    `CfmVolOfTargetFund`                decimal(16, 2)                                         NOT NULL COMMENT '目标理财产品的确认份数',
    `MinFee`                            decimal(10, 2)                                         NOT NULL COMMENT '最少收费',
    `OtherFee2`                         decimal(16, 2)                                         NOT NULL COMMENT '其他费用2',
    `TransferFee`                       decimal(10, 2)                                         NOT NULL COMMENT '过户费',
    `FromTAFlag`                        varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否注册登记人发起业务标志',
    `RedemptionInAdvanceFlag`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '预约赎回标志',
    `FrozenMethod`                      varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '冻结方式',
    `RedemptionReason`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '强行赎回原因',
    `TotalTransFee`                     decimal(10, 2)                                         NOT NULL COMMENT '交易确认费用合计',
    `AlternationDate`                   varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '最后更新日',
    `RefundAmount`                      decimal(16, 2)                                         NOT NULL COMMENT '退款金额',
    `SalePercent`                       decimal(8, 5)                                          NOT NULL COMMENT '配售比例',
    `ManagerRealRatio`                  decimal(7, 4)                                          NOT NULL COMMENT '实际计算折扣',
    `ChangeFee`                         decimal(16, 2)                                         NOT NULL COMMENT '转换费',
    `RecuperateFee`                     decimal(16, 2)                                         NOT NULL COMMENT '补差费',
    `AchievementPay`                    decimal(16, 2)                                         NOT NULL COMMENT '业绩报酬',
    `AchievementCompen`                 decimal(16, 2)                                         NOT NULL COMMENT '业绩补偿',
    `SharesAdjustmentFlag`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额强制调整标志',
    `GeneralTASerialNO`                 varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总TA确认流水号',
    `UndistributeMonetaryIncome`        decimal(16, 2)                                         NOT NULL COMMENT '货币式理财未付收益金额',
    `UndistributeMonetaryIncomeFlag`    varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '货币式理财未付收益金额正负',
    `BreachFee`                         decimal(16, 2)                                         NOT NULL COMMENT '违约金',
    `BreachFeeBackToFund`               decimal(16, 2)                                         NOT NULL COMMENT '违约金归理财资产金额',
    `PunishFee`                         decimal(16, 2)                                         NOT NULL COMMENT '惩罚性费用',
    `ChangeAgencyFee`                   decimal(16, 2)                                         NOT NULL COMMENT '转换代理费',
    `RecuperateAgencyFee`               decimal(16, 2)                                         NOT NULL COMMENT '补差代理费',
    `ErrorDetail`                       varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出错详细信息',
    `RaiseInterest`                     decimal(16, 2)                                         NOT NULL COMMENT '认购期间利息',
    `FeeCalculator`                     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '计费人',
    `ShareRegisterDate`                 varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额注册日期',
    `TotalFrozenVol`                    decimal(16, 2)                                         NOT NULL COMMENT '理财产品冻结总份数',
    `FundVolBalance`                    decimal(16, 2)                                         NOT NULL COMMENT '理财产品份数余额',
    `FrozenBalance`                     decimal(16, 2)                                         NOT NULL COMMENT '冻结金额',
    `TransferDateThroughClearingAgency` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '清算资金经清算人划出日期',
    `AppSheetSerialNo`                  varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请单编号',
    `TACode`                            varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'TA代码',
    `DistributorCode`                   varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '销售人代码',
    `ReferenceNumber`                   int                                                    NOT NULL COMMENT '同一个确认的序列号',
    PRIMARY KEY (`ConfirmID`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_expectation
-- ----------------------------
DROP TABLE IF EXISTS `transaction_expectation`;
CREATE TABLE `transaction_expectation`
(
    `AppSheetSerialNo`                  varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请单编号',
    `TransactionCfmDate`                varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易确认日期',
    `ConfirmedVol`                      decimal(16, 2)                                         NOT NULL COMMENT '理财账户交易确认份数',
    `ConfirmedAmount`                   decimal(16, 2)                                         NOT NULL COMMENT '每笔交易确认金额',
    `ReturnCode`                        varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易处理返回代码',
    `BusinessFinishFlag`                varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '业务过程完全结束标识',
    `RegionCode`                        varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '交易所在地区编号',
    `AgencyFee`                         decimal(10, 2)                                         NOT NULL COMMENT '代理费',
    `NAV`                               decimal(9, 6)                                          NOT NULL COMMENT '单位净值',
    `OtherFee1`                         decimal(10, 2)                                         NOT NULL COMMENT '其他费用1',
    `StampDuty`                         decimal(16, 2)                                         NOT NULL COMMENT '印花税',
    `RateFee`                           decimal(9, 8)                                          NOT NULL COMMENT '费率',
    `Specification`                     varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要/说明',
    `TransferDirection`                 varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '转入/转出标识',
    `Interest`                          decimal(10, 2)                                         NOT NULL COMMENT '理财账户利息金额',
    `VolumeByInterest`                  decimal(16, 2)                                         NOT NULL COMMENT '利息产生的理财产品份数',
    `InterestTax`                       decimal(16, 2)                                         NOT NULL COMMENT '利息税',
    `TradingPrice`                      decimal(7, 4)                                          NOT NULL COMMENT '交易价格',
    `Tax`                               decimal(16, 2)                                         NOT NULL COMMENT '税金',
    `TargetNAV`                         decimal(9, 6)                                          NOT NULL COMMENT '目标理财产品的单位净值',
    `TargetFundPrice`                   decimal(7, 4)                                          NOT NULL COMMENT '目标理财产品的价格',
    `CfmVolOfTargetFund`                decimal(16, 2)                                         NOT NULL COMMENT '目标理财产品的确认份数',
    `MinFee`                            decimal(10, 2)                                         NOT NULL COMMENT '最少收费',
    `OtherFee2`                         decimal(16, 2)                                         NOT NULL COMMENT '其他费用2',
    `TransferFee`                       decimal(10, 2)                                         NOT NULL COMMENT '过户费',
    `FromTAFlag`                        varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '是否注册登记人发起业务标志',
    `RedemptionInAdvanceFlag`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '预约赎回标志',
    `FrozenMethod`                      varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '冻结方式',
    `RedemptionReason`                  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '强行赎回原因',
    `TotalTransFee`                     decimal(10, 2)                                         NOT NULL COMMENT '交易确认费用合计',
    `AlternationDate`                   varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '最后更新日',
    `RefundAmount`                      decimal(16, 2)                                         NOT NULL COMMENT '退款金额',
    `SalePercent`                       decimal(8, 5)                                          NOT NULL COMMENT '配售比例',
    `ManagerRealRatio`                  decimal(7, 4)                                          NOT NULL COMMENT '实际计算折扣',
    `ChangeFee`                         decimal(16, 2)                                         NOT NULL COMMENT '转换费',
    `RecuperateFee`                     decimal(16, 2)                                         NOT NULL COMMENT '补差费',
    `AchievementPay`                    decimal(16, 2)                                         NOT NULL COMMENT '业绩报酬',
    `AchievementCompen`                 decimal(16, 2)                                         NOT NULL COMMENT '业绩补偿',
    `SharesAdjustmentFlag`              varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额强制调整标志',
    `UndistributeMonetaryIncome`        decimal(16, 2)                                         NOT NULL COMMENT '货币式理财未付收益金额',
    `UndistributeMonetaryIncomeFlag`    varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '货币式理财未付收益金额正负',
    `BreachFee`                         decimal(16, 2)                                         NOT NULL COMMENT '违约金',
    `BreachFeeBackToFund`               decimal(16, 2)                                         NOT NULL COMMENT '违约金归理财资产金额',
    `PunishFee`                         decimal(16, 2)                                         NOT NULL COMMENT '惩罚性费用',
    `ChangeAgencyFee`                   decimal(16, 2)                                         NOT NULL COMMENT '转换代理费',
    `RecuperateAgencyFee`               decimal(16, 2)                                         NOT NULL COMMENT '补差代理费',
    `ErrorDetail`                       varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出错详细信息',
    `RaiseInterest`                     decimal(16, 2)                                         NOT NULL COMMENT '认购期间利息',
    `FeeCalculator`                     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '计费人',
    `ShareRegisterDate`                 varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '份额注册日期',
    `TotalFrozenVol`                    decimal(16, 2)                                         NOT NULL COMMENT '理财产品冻结总份数',
    `FundVolBalance`                    decimal(16, 2)                                         NOT NULL COMMENT '理财产品份数余额',
    `FrozenBalance`                     decimal(16, 2)                                         NOT NULL COMMENT '冻结金额',
    `TransferDateThroughClearingAgency` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '清算资金经清算人划出日期',
    `Status`                            varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '表状态',
    PRIMARY KEY (`AppSheetSerialNo`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
