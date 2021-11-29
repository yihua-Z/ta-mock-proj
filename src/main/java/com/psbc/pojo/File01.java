package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File01 implements TableModel {
	private String address;
	private String instrepridcode;
	private String instrepridtype;
	private String instreprname;
	private String appsheetserialno;
	private String certificatetype;
	private String certificateno;
	private String investorname;
	private String transactiondate;
	private String transactiontime;
	private String individualorinstitution;
	private String postcode;
	private String transactorcertno;
	private String transactorcerttype;
	private String transactorname;
	private String transactionaccountid;
	private String distributorcode;
	private String businesscode;
	private String acctnooffminclearingagency;
	private String acctnameoffminclearingagency;
	private String clearingagencycode;
	private String investorsbirthday;
	private String depositacct;
	private String regioncode;
	private String educationlevel;
	private String emailaddress;
	private String faxno;
	private String vocationcode;
	private String hometelno;
	private String annualincome;
	private String mobiletelno;
	private String branchcode;
	private String officetelno;
	private String accountabbr;
	private String confidentialdocumentcode;
	private String sex;
	private String shsecuritiesaccountid;
	private String szsecuritiesaccountid;
	private String taaccountid;
	private String telno;
	private String tradingmethod;
	private String minorflag;
	private String delivertype;
	private String transactoridtype;
	private String accountcardid;
	private String multiacctflag;
	private String targettransactionaccountid;
	private String acctnameofinvestorinclearingagency;
	private String acctnoofinvestorinclearingagency;
	private String clearingagency;
	private String deliverway;
	private String nationality;
	private String netno;
	private String broker;
	private String corpname;
	private String certvaliddate;
	private String insttrancertvaliddate;
	private String instreprcertvaliddate;
	private String clientriskrate;
	private String instreprmanagerange;
	private String controlholder;
	private String actualcontroller;
	private String marriagestatus;
	private String familynum;
	private String penates;
	private String mediahobby;
	private String institutiontype;
	private String englishfirstname;
	private String englishfamliyname;
	private String vocation;
	private String corpoproperty;
	private String staffnum;
	private String hobbytype;
	private String province;
	private String city;
	private String county;
	private String commendperson;
	private String commendpersontype;
	private String acceptmethod;
	private String frozencause;
	private String freezingdeadline;
	private String originalserialno;
	private String originalappsheetno;
	private String specification;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File01(); }
}