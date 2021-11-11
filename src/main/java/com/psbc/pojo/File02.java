package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File02 implements TableModel {
	private String AppSheetSerialNo;
	private String TransactionCfmDate;
	private String ReturnCode;
	private String TransactionAccountID;
	private String DistributorCode;
	private String BusinessCode;
	private String TAAccountID;
	private String MultiAcctFlag;
	private String TASerialNO;
	private String TransactionDate;
	private String TransactionTime;
	private String BranchCode;
	private String FromTAFlag;
	private String CertificateType;
	private String CertificateNo;
	private String InvestorName;
	private String IndividualOrInstitution;
	private String AccountAbbr;
	private String AccountCardID;
	private String RegionCode;
	private String TargetTransactionAccountID;
	private String NetNo;
	private String Specification;
	private String CustomerNo;
	private String FrozenCause;
	private String FreezingDeadline;
	private String ErrorDetail;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File02(); }
}