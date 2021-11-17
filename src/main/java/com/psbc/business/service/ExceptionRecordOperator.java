package com.psbc.business.service;

import com.psbc.mapper.AccountConfirmationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountConfirmation;
import com.psbc.pojo.Exception;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.psbc.utils.DateAndTimeUtil.getNowDate;

@Data
@Component
public class ExceptionRecordOperator {
    @Autowired
    ExceptionDao exceptionDao;

    private String returnCode = "0000";
    private RecordOperator operator = new RecordOperator();
    public void errorOperate(AccountApplication application,String returnCode) {

//        将不合法的申请记录写入“异常登 记簿”(包括不合法原因)
//        returnCode
        Exception exception = new Exception();
        exception = (Exception) this.operator.getTargetObject(application, exception.newInstanceWithoutArgs());
        exception.setSpeification(this.returnCode);

        exceptionDao.insert(exception);
        this.returnCode=returnCode;
        this.generateFailed(application);

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


}
