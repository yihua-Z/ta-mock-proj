package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File03 implements TableModel {
	private String AppSheetSerialNo;
	private String FundCode;
	private String LargeRedemptionFlag;
	private String TransactionDate;
	private String TransactionTime;
	private String TransactionAccountID;
	private String DistributorCode;
	private String ApplicationVol;
	private String ApplicationAmount;
	private String BusinessCode;
	private String TAAccountID;
	private String DiscountRateOfCommission;
	private String DepositAcct;
	private String RegionCode;
	private String CurrencyType;
	private String BranchCode;
	private String OriginalAppSheetNo;
	private String OriginalSubsDate;
	private String IndividualOrInstitution;
	private String ValidPeriod;
	private String DaysRedemptionInAdvance;
	private String RedemptionDateInAdvance;
	private String OriginalSerialNo;
	private String DateOfPeriodicSubs;
	private String TASerialNO;
	private String TermOfPeriodicSubs;
	private String FutureBuyDate;
	private String TargetDistributorCode;
	private String Charge;
	private String TargetBranchCode;
	private String TargetTransactionAccountID;
	private String TargetRegionCode;
	private String DividendRatio;
	private String Specification;
	private String CodeOfTargetFund;
	private String TotalBackendLoad;
	private String ShareClass;
	private String OriginalCfmDate;
	private String DetailFlag;
	private String OriginalAppDate;
	private String DefDividendMethod;
	private String FrozenCause;
	private String FreezingDeadline;
	private String VarietyCodeOfPeriodicSubs;
	private String SerialNoOfPeriodicSubs;
	private String RationType;
	private String TargetTAAccountID;
	private String TargetRegistrarCode;
	private String NetNo;
	private String CustomerNo;
	private String TargetShareType;
	private String RationProtocolNo;
	private String BeginDateOfPeriodicSubs;
	private String EndDateOfPeriodicSubs;
	private String SendDayOfPeriodicSubs;
	private String Broker;
	private String SalesPromotion;
	private String AcceptMethod;
	private String ForceRedemptionType;
	private String TakeIncomeFlag;
	private String PurposeOfPeSubs;
	private String FrequencyOfPeSubs;
	private String PeriodSubTimeUnit;
	private String BatchNumOfPeSubs;
	private String CapitalMode;
	private String DetailCapticalMode;
	private String BackenloadDiscount;
	private String CombineNum;
	private String FutureSubscribeDate;
	private String TradingMethod;
	private String LargeBuyFlag;
	private String ChargeType;
	private String SpecifyRateFee;
	private String SpecifyFee;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File03(); }
}