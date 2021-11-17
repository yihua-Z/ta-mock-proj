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


    private ExceptionRecordOperator errorOperate = SpringContextUtil.getBean(ExceptionRecordOperator.class);
    private SucceedRecordOperator generateSucceed = SpringContextUtil.getBean(SucceedRecordOperator.class);

    private boolean checkAppSheetSeriaNo = false;
    private String returnCode = "0000";

    @Autowired
    AccountExpectationDao accountExpectationDao;

    public void ExpectationOperate(List<AccountApplication> accountApplications) {
        if (accountApplications != null) {

            for (AccountApplication accountApplication : accountApplications
            ) {
                checkAppSheetSeriaNo(accountApplication);
//          记录存在 Expectation 中
                if (this.checkAppSheetSeriaNo) {
                    this.generateSucceed.generateRecord(accountApplication);
                    if (this.returnCode.equals("0000")) {
                        this.generateSucceed.generateSucceed(accountApplication);
                    } else {
                        this.errorOperate.errorOperate(accountApplication);
                    }
                }
//            记录不存在 Expectation 中
                else {
//              校验记录的数据业务合法性
                    CheckDataLegality checkDataLegality = SpringContextUtil.getBean(CheckDataLegality.class);
                    checkDataLegality.Check(accountApplication);

                    {
//                        测试代码，为了强制失败
                        checkDataLegality.setLegality(false);
                        this.returnCode = "9999";
                    }

                    //              数据合法 生成记录
                    if (checkDataLegality.isLegality()) {
                        this.generateSucceed.generateRecord(accountApplication);
                    } else {
//                  数据不合法 异常登记， 原因
                        this.returnCode = checkDataLegality.getReturnCode();
                        this.errorOperate.errorOperate(accountApplication);
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

        String appsheetserialno = application.getAppsheetserialno();

        AccountExpectation expectation = accountExpectationDao.selectByPrimaryKey(appsheetserialno);

        if (expectation != null) {
            this.checkAppSheetSeriaNo = true;
        }

    }


}
