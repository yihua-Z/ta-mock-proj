package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_info
 * @author 
 */
@Data
public class AccountInfo implements Serializable {
    private Integer transactionaccountid;

    private Integer taacountid;

    private static final long serialVersionUID = 1L;
}