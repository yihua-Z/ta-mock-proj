package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dividend {
	private int DividendID;
	private Double BasisforCalculatingDividend;
	private Double VolOfDividendforReinvestment;
	private String DividentDate;
	private Double DividendAmount;
	private Double ConfirmedAmount;
	private String ReturnCode;
	private String TransactionAccountID;
	private String DistributorCode;
	private String BusinessCode;
	private String TAAccountID;
	private Double DividendPerUnit;
	private String DepositAcct;
	private String RegionCode;
	private String DownLoaddate;
	private Double Charge;
	private Double AgencyFee;
	private Double TotalFrozenVol;
	private String BranchCode;
	private Double OtherFee1;
	private Double OtherFee2;
	private String IndividualOrInstitution;
	private Double DividendRatio;
	private Double StampDuty;
	private Double FrozenBalance;
	private Double TransferFee;
	private String ShareClass;
	private String FeeCalculator;
	private Double DrawBonusUnit;
	private Double FrozenSharesforReinvest;
	private String DividendType;
	private String OriginalAppSheetNo;
	private Double AchievementPay;
	private Double AchievementCompen;
	private String TACode;
	private String TransactionCfmDate;
	private String FundCode;
}