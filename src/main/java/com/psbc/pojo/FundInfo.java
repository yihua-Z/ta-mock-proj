package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundInfo {
	private String FundCode;
	private String FundName;
	private Double TotalFundVol;
	private String FundStatus;
	private Double NAV;
	private String UpdateDate;
	private String NetValueType;
	private Double AccumulativeNAV;
	private String ConvertStatus;
	private String PeriodicStatus;
	private String TransferAgencyStatus;
	private Double FundSize;
	private String CurrencyType;
	private String AnnouncFlag;
	private String DefDividendMethod;
	private Double InstAppSubsAmnt;
	private Double InstAppSubsVol;
	private Double MinAmountByInst;
	private Double MinVolByInst;
	private String CustodianCode;
	private Double AmountOfPeriodicSubs;
	private String DateOfPeriodicSubs;
	private Double MaxRedemptionVol;
	private Double MinAccountBalance;
	private String IPOStartDate;
	private String IPOEndDate;
	private String FundManagerCode;
	private Double IndiAppSubsVol;
	private Double IndiAppSubsAmount;
	private Double MinSubsVolByIndi;
	private Double MinSubsAmountByIndi;
	private String RegistrarCode;
	private String FundSponsor;
	private Double TradingPrice;
	private Double FaceValue;
	private String DividentDate;
	private String RegistrationDate;
	private String XRDate;
	private Double MaxSubsVolByIndi;
	private Double MaxSubsAmountByIndi;
	private Double MaxSubsVolByInst;
	private Double MaxSubsAmountByInst;
	private Double UnitSubsVolByIndi;
	private Double UnitSubsAmountByIndi;
	private Double UnitSubsVolByInst;
	private Double UnitSubsAmountByInst;
	private Double MinBidsAmountByIndi;
	private Double MinBidsAmountByInst;
	private Double MinAppBidsAmountByIndi;
	private Double MinAppBidsAmountByInst;
	private Double MinRedemptionVol;
	private Double MinInterconvertVol;
	private String IssueTypeByIndi;
	private String IssueTypeByInst;
	private String SubsType;
	private String CollectFeeType;
	private String NextTradeDate;
	private Double ValueLine;
	private Double TotalDivident;
	private Double FundIncome;
	private String FundIncomeFlag;
	private Double Yield;
	private String YieldFlag;
	private Double GuaranteedNAV;
	private Double FundYearIncomeRate;
	private String FundYearIncomeRateFlag;
	private Double IndiMaxPurchase;
	private Double InstMaxPurchase;
	private Double IndiDayMaxSumBuy;
	private Double InstDayMaxSumBuy;
	private Double IndiDayMaxSumRedeem;
	private Double InstDayMaxSumRedeem;
	private Double IndiMaxRedeem;
	private Double InstMaxRedeem;
	private String FundDayIncomeFlag;
	private Double FundDayIncome;
	private String AllowBreachRedempt;
	private String FundType;
	private String FundTypeName;
	private String RegistrarName;
	private String FundManagerName;
	private String FundServerTel;
	private String FundInternetAddress;
	private String DistributorCode;
}