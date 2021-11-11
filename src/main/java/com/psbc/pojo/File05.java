package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File05 implements TableModel {
	private String AvailableVol;
	private String TotalVolOfDistributorInTA;
	private String TransactionCfmDate;
	private String FundCode;
	private String TransactionAccountID;
	private String DistributorCode;
	private String TAAccountID;
	private String TotalFrozenVol;
	private String BranchCode;
	private String TASerialNO;
	private String TotalBackendLoad;
	private String ShareClass;
	private String DetailFlag;
	private String AccountStatus;
	private String ShareRegisterDate;
	private String UndistributeMonetaryIncome;
	private String UndistributeMonetaryIncomeFlag;
	private String GuaranteedAmount;
	private String SourceType;
	private String DefDividendMethod;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File05(); }
}