package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountExpectation {
    private String AppSheetSerialNo;
    private String ReturnCode;
    private String ErrorDetail;
    private String Status;
    private String TransactionCfmDate;
    private String TASerialNO;
    private String FromTAFlag;
    private String RegionCode;
    private String NetNo;
    private String Specification;
}