package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.DatabaseModel;
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



    public boolean Check(DatabaseModel Application) {
        if(Application.getClass().getSimpleName().equals("File01")){
           AccountApplication accountApplication= (AccountApplication) Application;
            List<AccountApplication> accountApplications;
            accountApplications = accountApplicationDao.selectUnionPrimaryKey(accountApplication);
            if (accountApplications != null) {
                if (accountApplications.size() > 1) {
                    this.returnCode = this.ERRORCODE;
                    this.legality = false;
                    return false;
                }

                String transactionDate = accountApplication.getTransactionDate();
                String transactionTime = accountApplication.getTransactionTime();
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
