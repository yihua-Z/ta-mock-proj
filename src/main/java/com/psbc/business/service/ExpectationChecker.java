package com.psbc.business.service;

import com.psbc.TaMockProjectApplication;
import com.psbc.mapper.*;
import com.psbc.pojo.*;
import com.psbc.pojo.Exception;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.psbc.utils.DateAndTimeUtil.*;

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
            if (businessCode.equals("File01")) {
                AccountApplication accountApplication = (AccountApplication) application;
                accountApplication.setRecordStatus("1");
                accountApplicationDao.updateByPrimaryKey(accountApplication);
            }

            boolean checkAppSheetSeriaNo = checkAppSheetSeriaNo(application);
            return checkAppSheetSeriaNo;
        }
        return false;
    }


    @Autowired
    AccountApplicationDao accountApplicationDao;

    private boolean checkAppSheetSeriaNo(DatabaseModel application) {
        boolean checkAppSheetSeriaNo = false;

        this.expectation = accountExpectationDao.selectByPrimaryKey(((AccountApplication) application).getAppSheetSerialNo());
        if (expectation != null) {
            checkAppSheetSeriaNo = true;
        }
        return checkAppSheetSeriaNo;
    }


}
