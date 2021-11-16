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


    public String generateRecord() {
        String returnCode = "0000";
//        Expectation中由对应的记录
//        获取确认记录中需要赋值的字段
//        生成相应字段的值
//        生成 returnCode
        return returnCode;
    }

    public void generateSucceed() {
//                生成对应确认成功记录
//                写入""account_info"表
//                初始化"acct_share"表
//                写入"account_confirmation"表
    }


    public String errorOperate() {
//        生成 returnCode
//        将不合法的申请记录写入“异常登 记簿”(包括不合法原因)
        return "9999";
    }


    public void generateFailed() {
//        生成对应确认失败记录
//        写入 "account_confirmation" 表
    }

    public void ExpectationOperate(List<AccountApplication> accountApplications) {

        for (AccountApplication accountApplication : accountApplications
        ) {
            checkAppSheetSeriaNo(accountApplication);
//          记录存在 Expectation 中
            if (this.checkAppSheetSeriaNo) {
                String returnCode = this.generateRecord();
//                判断returnCode = 0000?
                if (returnCode.equals("0000")) {
                    this.generateSucceed();
                }
            }
//            记录不存在 Expectation 中
            else{
//              校验记录的数据业务合法性
                CheckDataLegality checkDataLegality = new CheckDataLegality();
                boolean legality = checkDataLegality.Check();
//              数据合法 生成记录
                if (legality) {
                    this.generateRecord();
                } else {
//                  数据不合法 异常登记 原因
                    String returncode = this.errorOperate();

                }

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
