package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File07 implements TableModel {
	private String FundName;
	private String TotalFundVol;
	private String FundCode;
	private String FundStatus;
	private String NAV;
	private String UpdateDate;
	private String NetValueType;
	private String AccumulativeNAV;
	private String ConvertStatus;
	private String PeriodicStatus;
	private String TransferAgencyStatus;
	private String FundSize;
	private String CurrencyType;
	private String AnnouncFlag;
	private String DefDividendMethod;
	private String InstAppSubsAmnt;
	private String InstAppSubsVol;
	private String MinAmountByInst;
	private String MinVolByInst;
	private String CustodianCode;
	private String AmountOfPeriodicSubs;
	private String DateOfPeriodicSubs;
	private String MaxRedemptionVol;
	private String MinAccountBalance;
	private String IPOStartDate;
	private String IPOEndDate;
	private String FundManagerCode;
	private String IndiAppSubsVol;
	private String IndiAppSubsAmount;
	private String MinSubsVolByIndi;
	private String MinSubsAmountByIndi;
	private String RegistrarCode;
	private String FundSponsor;
	private String TradingPrice;
	private String FaceValue;
	private String DividentDate;
	private String RegistrationDate;
	private String XRDate;
	private String MaxSubsVolByIndi;
	private String MaxSubsAmountByIndi;
	private String MaxSubsVolByInst;
	private String MaxSubsAmountByInst;
	private String UnitSubsVolByIndi;
	private String UnitSubsAmountByIndi;
	private String UnitSubsVolByInst;
	private String UnitSubsAmountByInst;
	private String MinBidsAmountByIndi;
	private String MinBidsAmountByInst;
	private String MinAppBidsAmountByIndi;
	private String MinAppBidsAmountByInst;
	private String MinRedemptionVol;
	private String MinInterconvertVol;
	private String IssueTypeByIndi;
	private String IssueTypeByInst;
	private String SubsType;
	private String CollectFeeType;
	private String NextTradeDate;
	private String ValueLine;
	private String TotalDivident;
	private String FundIncome;
	private String FundIncomeFlag;
	private String Yield;
	private String YieldFlag;
	private String GuaranteedNAV;
	private String FundYearIncomeRate;
	private String FundYearIncomeRateFlag;
	private String IndiMaxPurchase;
	private String InstMaxPurchase;
	private String IndiDayMaxSumBuy;
	private String InstDayMaxSumBuy;
	private String IndiDayMaxSumRedeem;
	private String InstDayMaxSumRedeem;
	private String IndiMaxRedeem;
	private String InstMaxRedeem;
	private String FundDayIncomeFlag;
	private String FundDayIncome;
	private String AllowBreachRedempt;
	private String FundType;
	private String FundTypeName;
	private String RegistrarName;
	private String FundManagerName;
	private String FundServerTel;
	private String FundInternetAddress;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File07(); }
}