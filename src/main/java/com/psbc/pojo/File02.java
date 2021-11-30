package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File02 implements TableModel {

	private String appsheetserialno;
	private String transactioncfmdate;
	private String returncode;
	private String transactionaccountid;
	private String distributorcode;
	private String businesscode;
	private String taaccountid;
	private String multiacctflag;
	private String taserialno;
	private String transactiondate;
	private String transactiontime;
	private String branchcode;
	private String fromtaflag;
	private String certificatetype;
	private String certificateno;
	private String investorname;
	private String individualorinstitution;
	private String accountabbr;
	private String accountcardid;
	private String regioncode;
	private String targettransactionaccountid;
	private String netno;
	private String specification;
	private String customerno;
	private String frozencause;
	private String freezingdeadline;
	private String errordetail;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File02(); }
}