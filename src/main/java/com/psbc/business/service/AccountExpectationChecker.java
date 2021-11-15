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
    AccountExpectationDao AccountExpectationDao;

    private RecordOperator operator = new RecordOperator();
    private AccountConfirmation accountConfirmation = new AccountConfirmation();

    public void ExpectationOperate(AccountApplication accountApplication, AccountExpectation accountExpectation) {


        Object targetObject = this.operator.getTargetObject(accountExpectation, this.accountConfirmation);


    }

    public List<AccountExpectation> getAllAccountExpectation() {
        List<AccountExpectation> accountExpectations = AccountExpectationDao.selectAll();
        return accountExpectations;

    }

    public List<String> getExpectationList() {

        List<AccountExpectation> accountExpectations = AccountExpectationDao.selectAll();

        List<String> expectations = new ArrayList<>();



        return expectations;
    }
}
