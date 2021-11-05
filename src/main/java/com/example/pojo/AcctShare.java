package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcctShare {
	private String TAAccountID;
	private String DistributorCode;
	private Double TotalVolOfDistributorInTA;
	private Double TotalFrozenVol;
	private String TransactionCfmDate;
}