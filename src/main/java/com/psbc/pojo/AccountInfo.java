package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
	private String TAAccountID;
	private String Name;
	private String Sex;
	private String Nationality;
	private String Vocation;
	private String Address;
	private String TelNo;
	private String CertificateType;
	private String CertificateNo;
	private String CertValidDate;
	private String TransactionAccountID;
	private String DistributorCode;
	private String BranchCode;
	private String AccountStatus;
	private String TACode;
	private String CustomerNo;
}