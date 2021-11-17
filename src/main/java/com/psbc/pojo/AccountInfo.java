package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_info
 * @author 
 */
@Data
public class AccountInfo implements DatabaseModel {
    private String transactionaccountid;

    private String taacountid;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountInfo();
    }
}