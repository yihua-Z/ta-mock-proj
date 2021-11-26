package com.psbc.business.service;

import com.psbc.mapper.*;
import com.psbc.pojo.*;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Data
@Component
public class ExpectationChecker {


    private ExceptionRecordOperator errorOperate;
    private SucceedRecordOperator generateSucceed;
    private AccountExpectation expectation;
    private CheckDataLegality checkDataLegality;


    private String returnCode = "0000";
    private String businessCode = "";

    private static final Logger logger = Logger.getLogger(ExpectationChecker.class);

    @Autowired
    AccountExpectationDao accountExpectationDao;


    @Transactional
    public boolean ExpectationOperate(DatabaseModel application) {

        if (application != null) {
            String businessCode = application.getClass().getSimpleName();
            if (businessCode.equals("AccountApplication")) {
                AccountApplication accountApplication = (AccountApplication) application;
                accountApplication.setRecordStatus("1");
                accountApplicationDao.updateByPrimaryKey(accountApplication);
            }

            boolean checkAppSheetSeriaNo = checkPrimaryKey(application);
            return checkAppSheetSeriaNo;
        }
        return false;
    }


    @Autowired
    AccountApplicationDao accountApplicationDao;

    private boolean checkPrimaryKey(DatabaseModel application) {
        boolean checkAppSheetSeriaNo = false;
        RecordOperator recordOperator = new RecordOperator();
        AccountExpectation accountExpectation = new AccountExpectation();
        accountExpectation = (AccountExpectation) recordOperator.getTargetObject(application, accountExpectation.newInstanceWithoutArgs());
        this.expectation = accountExpectationDao.selectByPrimaryKey(accountExpectation);
        if (expectation != null) {
            checkAppSheetSeriaNo = true;
        }
        return checkAppSheetSeriaNo;
    }


}
