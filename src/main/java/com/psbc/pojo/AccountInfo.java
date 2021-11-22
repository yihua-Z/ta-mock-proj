package com.psbc.pojo;

import java.io.Serializable;
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

    private String taaccountid;


    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new AccountInfo();
    }
}