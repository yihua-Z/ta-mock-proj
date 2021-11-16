package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.AccountExpectationDao;
import com.psbc.pojo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class AccountExpectationChecker {
    @Autowired
    AccountExpectationDao accountExpectationDao;

    private RecordOperator operator = new RecordOperator();
    private AccountConfirmation accountConfirmation = new AccountConfirmation();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();

    private boolean checkAppSheetSeriaNo = false;


    public void ExpectationOperate(List<AccountApplication> accountApplications) {

        for (AccountApplication accountApplication : accountApplications
        ) {
            checkAppSheetSeriaNo(accountApplication);
            if (this.checkAppSheetSeriaNo) {

                CheckDataLegality checkDataLegality = new CheckDataLegality();
                boolean legality = checkDataLegality.Check();
                if()

//                Expectation中由对应的记录
//                获取确认记录中需要赋值的字段
//                生成相应字段的值
//                returnCode = 0000?
//                生成对应确认成功记录
//                写入""account_info"表
//                初始化"acct_share"表
//                写入"account_confirmation"表
            } else {
//                校验记录的数据业务合法性
//                是否合法


            }
        }

    }

    @Autowired
    AccountApplicationDao accountApplicationDao;

    public void ExpectationOperate() {

        List<AccountApplication> accountApplications = accountApplicationDao.selectAll();

        this.ExpectationOperate(accountApplications);

    }

    private void checkAppSheetSeriaNo(AccountApplication application) {

        String appsheetserialno = application.getAppsheetserialno();

        AccountExpectation expectation = accountExpectationDao.selectByPrimaryKey(appsheetserialno);

        if (expectation != null) {
            AccountConfirmation accountConfirmation;
            String returncode = expectation.getReturncode();
            this.checkAppSheetSeriaNo = true;
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
