package com.psbc.business.service;

import com.psbc.TaMockProjectApplication;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.mapper.AccountConfirmationDao;
import com.psbc.mapper.ExceptionDao;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountConfirmation;
import com.psbc.pojo.DatabaseModel;
import com.psbc.pojo.Exception;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.psbc.utils.DateAndTimeUtil.getNowDate;

@Data
@Component
public class ExceptionRecordOperator {
    @Autowired
    ExceptionDao exceptionDao;

    private String returnCode = "0000";
    private RecordOperator operator = new RecordOperator();

    @Transactional
    public void errorOperate(DatabaseModel application, String returnCode) {

//        将不合法的申请记录写入“异常登 记簿”(包括不合法原因)
//        returnCode
        Exception exception = new Exception();
        exception = (Exception) this.operator.getTargetObject(application, exception.newInstanceWithoutArgs());
        exception.setSpeification(this.returnCode);

        exceptionDao.insert(exception);
        this.returnCode = returnCode;
        this.generateFailed(application);

    }

    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);


    @Autowired
    AccountConfirmationDao accountConfirmationDao;
    @Autowired
    AccountApplicationDao accountApplicationDao;

    @Transactional
    public void generateFailed(DatabaseModel Application) {
        //        生成对应确认失败记录
        //        写入 "account_confirmation" 表
        AccountConfirmation accountConfirmation = new AccountConfirmation();
        accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(Application, accountConfirmation.newInstanceWithoutArgs());
        accountConfirmation.setReturncode(this.returnCode);
        accountConfirmation.setTransactioncfmdate(getNowDate());


        try {
            accountConfirmationDao.insert(accountConfirmation);
            if (Application.getClass().getSimpleName().equals("File01")) {
                AccountApplication accountApplication = (AccountApplication) Application;
                accountApplication.setRecordStatus("2");
                accountApplicationDao.updateByPrimaryKey(accountApplication);
            }


        } catch (java.lang.Exception e) {
            logger.error(e);
        }


    }


}
