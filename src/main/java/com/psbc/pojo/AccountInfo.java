package com.psbc.pojo;

import lombok.Data;

/**
 * account_info
 * @author 
 */
@Data
public class AccountInfo implements DatabaseModel {
    /**
     * 账户信息ID
     */
    private Integer accountinfoid;

    private String transactionaccountid;

    private String taacountid;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AcctShare();
    }
}