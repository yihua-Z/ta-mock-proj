package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionExpectation {
	private String AppSheetSerialNo;
	private String TransactionCfmDate;
	private Double ConfirmedVol;
	private Double ConfirmedAmount;
	private String ReturnCode;
	private String BusinessFinishFlag;
	private String RegionCode;
	private Double AgencyFee;
	private Double NAV;
	private Double OtherFee1;
	private Double StampDuty;
	private Double RateFee;
	private String Specification;
	private String TransferDirection;
	private Double Interest;
	private Double VolumeByInterest;
	private Double InterestTax;
	private Double TradingPrice;
	private Double Tax;
	private Double TargetNAV;
	private Double TargetFundPrice;
	private Double CfmVolOfTargetFund;
	private Double MinFee;
	private Double OtherFee2;
	private Double TransferFee;
	private String FromTAFlag;
	private String RedemptionInAdvanceFlag;
	private String FrozenMethod;
	private String RedemptionReason;
	private Double TotalTransFee;
	private String AlternationDate;
	private Double RefundAmount;
	private Double SalePercent;
	private Double ManagerRealRatio;
	private Double ChangeFee;
	private Double RecuperateFee;
	private Double AchievementPay;
	private Double AchievementCompen;
	private String SharesAdjustmentFlag;
	private Double UndistributeMonetaryIncome;
	private String UndistributeMonetaryIncomeFlag;
	private Double BreachFee;
	private Double BreachFeeBackToFund;
	private Double PunishFee;
	private Double ChangeAgencyFee;
	private Double RecuperateAgencyFee;
	private String ErrorDetail;
	private Double RaiseInterest;
	private String FeeCalculator;
	private String ShareRegisterDate;
	private Double TotalFrozenVol;
	private Double FundVolBalance;
	private Double FrozenBalance;
	private String TransferDateThroughClearingAgency;
	private String Status;
}