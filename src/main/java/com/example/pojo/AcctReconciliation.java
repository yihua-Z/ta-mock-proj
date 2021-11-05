package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcctReconciliation {
	private int ReconciliationID;
	private Double AvailableVol;
	private Double TotalVolOfDistributorInTA;
	private String TransactionCfmDate;
	private Double TotalFrozenVol;
	private String TASerialNO;
	private Double TotalBackendLoad;
	private String ShareClass;
	private String DetailFlag;
	private String ShareRegisterDate;
	private Double UndistributeMonetaryIncome;
	private String UndistributeMonetaryIncomeFlag;
	private Double GuaranteedAmount;
	private String SourceType;
	private String FundCode;
	private String DistributorCode;
}