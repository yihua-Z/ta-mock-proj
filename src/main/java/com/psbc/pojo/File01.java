package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File01 implements TableModel {
	private String Address;
	private String InstReprIDCode;
	private String InstReprIDType;
	private String InstReprName;
	private String AppSheetSerialNo;
	private String CertificateType;
	private String CertificateNo;
	private String InvestorName;
	private String TransactionDate;
	private String TransactionTime;
	private String IndividualOrInstitution;
	private String PostCode;
	private String TransactorCertNo;
	private String TransactorCertType;
	private String TransactorName;
	private String TransactionAccountID;
	private String DistributorCode;
	private String BusinessCode;
	private String AcctNoOfFMInClearingAgency;
	private String AcctNameOfFMInClearingAgency;
	private String ClearingAgencyCode;
	private String InvestorsBirthday;
	private String DepositAcct;
	private String RegionCode;
	private String EducationLevel;
	private String EmailAddress;
	private String FaxNo;
	private String VocationCode;
	private String HomeTelNo;
	private String AnnualIncome;
	private String MobileTelNo;
	private String BranchCode;
	private String OfficeTelNo;
	private String AccountAbbr;
	private String ConfidentialDocumentCode;
	private String Sex;
	private String SHSecuritiesAccountID;
	private String SZSecuritiesAccountID;
	private String TAAccountID;
	private String TelNo;
	private String TradingMethod;
	private String MinorFlag;
	private String DeliverType;
	private String TransactorIDType;
	private String AccountCardID;
	private String MultiAcctFlag;
	private String TargetTransactionAccountID;
	private String AcctNameOfInvestorInClearingAgency;
	private String AcctNoOfInvestorInClearingAgency;
	private String ClearingAgency;
	private String DeliverWay;
	private String Nationality;
	private String NetNo;
	private String Broker;
	private String CorpName;
	private String CertValidDate;
	private String InstTranCertValidDate;
	private String InstReprCertValidDate;
	private String ClientRiskRate;
	private String InstReprManageRange;
	private String ControlHolder;
	private String ActualController;
	private String MarriageStatus;
	private String FamilyNum;
	private String Penates;
	private String MediaHobby;
	private String InstitutionType;
	private String EnglishFirstName;
	private String EnglishFamliyName;
	private String Vocation;
	private String CorpoProperty;
	private String StaffNum;
	private String Hobbytype;
	private String Province;
	private String City;
	private String County;
	private String CommendPerson;
	private String CommendPersonType;
	private String AcceptMethod;
	private String FrozenCause;
	private String FreezingDeadline;
	private String OriginalSerialNo;
	private String OriginalAppSheetNo;
	private String Specification;

	@Override
	public TableModel newInstanceWithoutArgs() { return new File01(); }
}