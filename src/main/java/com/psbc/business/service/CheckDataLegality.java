package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.pojo.AccountApplication;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.psbc.utils.DateAndTimeUtil.getNowDateTime;

@Data
@Component
public class CheckDataLegality {
    private String returnCode = "0000";
    private final String ERRORCODE = "9999";
    private boolean legality = true;

    @Autowired
    AccountApplicationDao accountApplicationDao;



    public boolean Check(AccountApplication accountApplication) {
        List<AccountApplication> accountApplications;
        accountApplications = accountApplicationDao.selectUnionPrimaryKey(accountApplication);
        if (accountApplications != null) {
            if (accountApplications.size() > 1) {
                this.returnCode = this.ERRORCODE;
                this.legality = false;
                return false;
            }

            String transactionDate = accountApplication.getTransactiondate();
            String transactionTime = accountApplication.getTransactiontime();
            if (transactionDate != null & transactionTime != null) {
                String transactionDateTime = transactionDate + transactionTime;
                if (Double.valueOf(transactionDateTime) > Double.valueOf(getNowDateTime())) {
                    this.returnCode = this.ERRORCODE;
                    this.legality = false;
                }
            }

        }

        return this.legality;
    }

}
