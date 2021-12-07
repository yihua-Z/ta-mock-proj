package com.psbc.business.backup;

import com.psbc.mapper.*;
import com.psbc.pojo.*;
import com.psbc.pojo.DatabaseModel;
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
    private TransactionExpectation transactionExpectation;
    private CheckDataLegality checkDataLegality;


    private String returnCode = "0000";
    private String businessCode = "";

    private static final Logger logger = Logger.getLogger(ExpectationChecker.class);

    @Autowired
    AccountExpectationDao accountExpectationDao;

    @Autowired
    TransactionApplicationDao transactionApplicationDao;

    @Autowired
    TransactionExpectationDao transactionExpectationDao;




    @Transactional
    public boolean ExpectationOperate(DatabaseModel application) {

        if (application != null) {
            String businessCode = application.getClass().getSimpleName();
            if (businessCode.equals("AccountApplication")) {
                AccountApplication accountApplication = (AccountApplication) application;
                accountApplication.setRecordstatus("1");
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

    @Transactional
    public boolean ExpectationTransactionOperate(DatabaseModel application ) {

        if (application != null) {

            boolean checkAppSheetSeriaNo = checkTransactionPrimaryKey(application);
            return checkAppSheetSeriaNo;
        }
        return false;
    }

    private boolean checkTransactionPrimaryKey(DatabaseModel application) {
        boolean checkAppSheetSeriaNo = false;
        RecordOperator recordOperator = new RecordOperator();
        TransactionExpectation transactionExpectation = new TransactionExpectation();
        transactionExpectation=(TransactionExpectation)recordOperator.getTargetObject(application,transactionExpectation.newInstanceWithoutArgs());
        this.transactionExpectation = transactionExpectationDao.selectByPrimaryKey(transactionExpectation);
        if (transactionExpectation != null) {
            checkAppSheetSeriaNo = true;
        }
        return checkAppSheetSeriaNo;
    }


}
