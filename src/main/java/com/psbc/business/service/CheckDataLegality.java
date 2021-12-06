package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.TransactionApplicationDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.DatabaseModel;
import com.psbc.pojo.TransactionApplication;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.psbc.utils.DateAndTimeUtil.getFullNowDateTime;

@Data
@Component
public class CheckDataLegality {
    private String returnCode = "0000";
    private final String ERRORCODE = "9999";
    private boolean legality = true;

    @Autowired
    AccountApplicationDao accountApplicationDao;
    @Autowired
    TransactionApplicationDao transactionApplicationDao;



    public boolean Check(DatabaseModel Application) {
        if(Application.getClass().getSimpleName().equals("AccountApplication")){
           AccountApplication accountApplication= (AccountApplication) Application;
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
                    if (Double.valueOf(transactionDateTime) > Double.valueOf(getFullNowDateTime())) {
                        this.returnCode = this.ERRORCODE;
                        this.legality = false;
                    }
                }

            }

        }

        return this.legality;
    }

}
