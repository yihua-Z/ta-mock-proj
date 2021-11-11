package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File06 implements TableModel {
	private String BasisforCalculatingDividend;
	private String TransactionCfmDate;
	private String CurrencyType;
	private String VolOfDividendforReinvestment;
	private String DividentDate;
	private String DividendAmount;
	private String XRDate;
	private String ConfirmedAmount;
	private String FundCode;
	private String RegistrationDate;
	private String ReturnCode;
	private String TransactionAccountID;
	private String DistributorCode;
	private String BusinessCode;
	private String TAAccountID;
	private String DividendPerUnit;
	private String DefDividendMethod;
	private String DepositAcct;
	private String RegionCode;
	private String DownLoaddate;
	private String Charge;
	private String AgencyFee;
	private String TotalFrozenVol;
	private String NAV;
	private String BranchCode;
	private String OtherFee1;
	private String OtherFee2;
	private String IndividualOrInstitution;
	private String DividendRatio;
	private String TASerialNO;
	private String StampDuty;
	private String FrozenBalance;
	private String TransferFee;
	private String ShareClass;
	private String FeeCalculator;
	private String DrawBonusUnit;
	private String FrozenSharesforReinvest;
	private String DividendType;
	private String OriginalAppSheetNo;
	private String AchievementPay;
	private String AchievementCompen;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File06(); }
}