package com.psbc.business.service;

import com.psbc.mapper.*;
import com.psbc.pojo.*;
import com.psbc.pojo.Exception;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private boolean checkAppSheetSeriaNo = false;
    private String returnCode = "0000";

    @Autowired
    AccountExpectationDao accountExpectationDao;

    public void ExpectationFlow(AccountApplication accountApplication) {

        if (this.returnCode.equals("0000")) {
            this.generateSucceed.generateSucceed(accountApplication);
        } else {
            this.errorOperate.errorOperate(accountApplication, this.returnCode);
        }

    }

    public void ExpectationOperate(List<AccountApplication> accountApplications) {
        if (accountApplications != null) {
            for (AccountApplication accountApplication : accountApplications
            ) {
                checkAppSheetSeriaNo(accountApplication);
//                记录存在 Expectation 中
                if (this.checkAppSheetSeriaNo) {
//                    使用expectation的returnCode
                    this.returnCode = this.generateSucceed.generateRecord(accountApplication, expectation);
                    ExpectationFlow(accountApplication);
                }
//                记录不存在 Expectation 中
                else {
//                    校验记录的数据业务合法性
                    this.checkDataLegality.Check(accountApplication);
//                  数据合法 生成记录
                    if (this.checkDataLegality.isLegality()) {
//                        使用合法性检查后checkDataLegality的returnCode
                        this.generateSucceed.generateRecord(accountApplication, this.checkDataLegality.getReturnCode());
                        ExpectationFlow(accountApplication);
                    } else {
//                  数据不合法 异常登记
                        this.returnCode = this.checkDataLegality.getReturnCode();
                        this.errorOperate.errorOperate(accountApplication, this.returnCode);
                    }

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
        this.expectation = accountExpectationDao.selectByPrimaryKey(application.getAppSheetSerialNo());
        if (expectation != null) {
            this.checkAppSheetSeriaNo = true;
        }
    }


}
