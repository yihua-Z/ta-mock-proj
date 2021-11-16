package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.AccountExpectationDao;
import com.psbc.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountExpectationChecker {
    @Autowired
    AccountExpectationDao accountExpectationDao;

    private RecordOperator operator = new RecordOperator();
    private AccountConfirmation accountConfirmation = new AccountConfirmation();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();

    public void ExpectationOperate(List<AccountApplication> accountApplications) {


        checkAppSheetSeriaNo(accountApplications);

    }

    @Autowired
    AccountApplicationDao accountApplicationDao;

    public void ExpectationOperate() {

        List<AccountApplication> accountApplications = accountApplicationDao.selectAll();

        checkAppSheetSeriaNo(accountApplications);
    }

    private void checkAppSheetSeriaNo(List<AccountApplication> accountApplications) {


        for (AccountApplication application : accountApplications
        ) {
            String appsheetserialno = application.getAppsheetserialno();

            AccountExpectation expectation = accountExpectationDao.selectByPrimaryKey(appsheetserialno);

            if (expectation != null) {
                AccountConfirmation accountConfirmation;

                String returncode = expectation.getReturncode();

                AccountConfirmation targetObject = (AccountConfirmation) this.operator.getTargetObject(application, this.accountConfirmation);


            }
        }
    }


    public List<AccountExpectation> getAllAccountExpectation() {
        List<AccountExpectation> accountExpectations = accountExpectationDao.selectAll();
        return accountExpectations;

    }

    public List<String> getExpectationList() {

        List<AccountExpectation> allAccountExpectation = this.getAllAccountExpectation();
        List<String> expectations = new ArrayList<>();
        for (AccountExpectation e : allAccountExpectation
        ) {
            String appsheetserialno = e.getAppsheetserialno();
            expectations.add(appsheetserialno);
        }
        return expectations;
    }
}
