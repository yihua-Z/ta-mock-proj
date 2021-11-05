package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountConfirmation {
	private int ConfirmID;
	private String TransactionCfmDate;
	private String ReturnCode;
	private String BusinessCode;
	private String TransactionDate;
	private String TransactionTime;
	private String TASerialNO;
	private String FromTAFlag;
	private String RegionCode;
	private String NetNo;
	private String Specification;
	private String CustomerNo;
	private String ErrorDetail;
	private String AppSheetSerialNo;
	private String TACode;
	private String TAAccountID;
	private String DistributorCode;
	private int ReferenceNumber;
}