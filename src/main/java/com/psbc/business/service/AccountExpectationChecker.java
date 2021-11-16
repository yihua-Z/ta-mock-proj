package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.AccountConfirmationDao;
import com.psbc.mapper.AccountExpectationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.*;
import com.psbc.pojo.Exception;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.psbc.utils.DateAndTimeUtil.*;

@Data
@Component
public class AccountExpectationChecker {


    private RecordOperator operator = new RecordOperator();
    private AccountConfirmation accountConfirmation = new AccountConfirmation();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();

    private boolean checkAppSheetSeriaNo = false;
    private String returnCode = "0000";

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

    @Autowired
    ExceptionDao exceptionDao;

    public void errorOperate(AccountApplication application) {
//        生成 returnCode
//        将不合法的申请记录写入“异常登 记簿”(包括不合法原因)

        Exception exception = new Exception();
        exception = (Exception) this.operator.getTargetObject(application, exception.newInstanceWithoutArgs());
        exception.setSpeification(this.returnCode);
        exceptionDao.insert(exception);

    }

    @Autowired
    AccountConfirmationDao accountConfirmationDao;

    public void generateFailed(AccountApplication accountApplication) {
        //        生成对应确认失败记录
        //        写入 "account_confirmation" 表
        AccountConfirmation accountConfirmation = new AccountConfirmation();
        accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(accountApplication, accountConfirmation.newInstanceWithoutArgs());
        accountConfirmation.setReturncode(this.returnCode);
        accountConfirmation.setTransactioncfmdate(getNowDate());
        accountConfirmationDao.insert(accountConfirmation);
    }

    @Autowired
    AccountExpectationDao accountExpectationDao;

    public void ExpectationOperate(List<AccountApplication> accountApplications) {

        for (AccountApplication accountApplication : accountApplications
        ) {
            accountApplication.setTacode("0");
            accountApplication.setReferenceno(0);

            checkAppSheetSeriaNo(accountApplication);
//          记录存在 Expectation 中
            if (this.checkAppSheetSeriaNo) {
                this.generateRecord();
                if (this.returnCode.equals("0000")) {
                    this.generateSucceed();
                } else {
                    this.errorOperate(accountApplication);
                }
            }
//            记录不存在 Expectation 中
            else {
//              校验记录的数据业务合法性
                CheckDataLegality checkDataLegality = SpringContextUtil.getBean(CheckDataLegality.class);
                checkDataLegality.Check(accountApplication);

                {
                    checkDataLegality.setLegality(false);
                    this.returnCode = "9999";
                }

                //              数据合法 生成记录
                if (checkDataLegality.isLegality()) {
                    this.generateRecord();
                } else {
//                  数据不合法 异常登记 原因
                    this.returnCode = checkDataLegality.getReturnCode();
                    this.errorOperate(accountApplication);
                    this.generateFailed(accountApplication);
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
