package com.psbc.business.service;

import com.psbc.mapper.AccountExpectationDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountConfirmation;
import com.psbc.pojo.AccountExpectation;
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

    public void ExpectationOperate(List<AccountApplication> accountApplication) {

        for (AccountApplication application : accountApplication
        ) {
            String appsheetserialno = application.getAppsheetserialno();

            AccountExpectation expectation = accountExpectationDao.selectByPrimaryKey(appsheetserialno);

            if (expectation != null) {
                System.out.println("exists");
                expectation.getDistributorcode();
                expectation.getReferencenumber();
                expectation.getReturncode();
                expectation.getTacode();
            }
        }

//        Object targetObject = this.operator.getTargetObject(accountExpectation, this.accountConfirmation);


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
