package com.psbc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundDate {
	private String TACode;
	private String FundCode;
	private String DistributorCode;
	private String DateType;
	private Double CorrCfmDate;
	private String TransactionCfmDate;
}