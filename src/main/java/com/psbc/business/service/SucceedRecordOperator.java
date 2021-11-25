package com.psbc.business.service;

import com.psbc.TaMockProjectApplication;
import com.psbc.mapper.*;
import com.psbc.pojo.*;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;
import java.math.BigDecimal;

import static com.psbc.utils.DateAndTimeUtil.*;

@Data
@Component
public class SucceedRecordOperator {

    private RecordOperator operator = new RecordOperator();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();
    private String returnCode;
    private AccountConfirmation accountConfirmation = new AccountConfirmation();

    @Autowired
    TaPropertyDao taPropertyDao;

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    AcctShareDao acctShareDao;

    @Autowired
    AccountConfirmationDao accountConfirmationDao;

    @Autowired
    AccountApplicationDao accountApplicationDao;
    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    public String generateRecord(DatabaseModel application, AccountExpectation expectation) {

        AccountConfirmation accountConfirmation = new AccountConfirmation();
        accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());
        if (expectation != null) {
            this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(expectation, accountConfirmation);
        }

        this.getAccountConfirmation(application, "0");

        this.returnCode = expectation.getReturncode();

        return this.returnCode;
    }

    public DatabaseModel getAccountConfirmation(DatabaseModel application, String taCode) {
//      taCode应该配置
        TaProperty taProperty = taPropertyDao.selectByPrimaryKey(taCode);
        String accountprefix = taProperty.getAccountPrefix();
        Integer accountindex = taProperty.getAccountIndex();

        if (application.getClass().getSimpleName().equals("File01")) {
            AccountApplication accountApplication = (AccountApplication) application;
            this.accountConfirmation.setBusinesscode("1" + accountApplication.getBusinessCode().substring(1));
        }

//      @TODO
//      TaAccountid 随机生成待改进
        this.accountConfirmation.setTaaccountid(accountprefix + accountindex + (int) (Math.random() * (100000000)));
        this.accountConfirmation.setTransactioncfmdate(getNowDate());
        this.accountConfirmation.setTaserialno(getFullNowDateTime() + (int) (Math.random() * (1000)));
        this.accountConfirmation.setFromtaflag("0");
        this.accountConfirmation.setRegioncode("0001");
        this.accountConfirmation.setReturncode(this.returnCode);

        return this.accountConfirmation;

    }

    public void generateRecord(DatabaseModel application, String returnCode) {

        AccountConfirmation accountConfirmation = new AccountConfirmation();
        this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());
        this.returnCode = returnCode;
        this.getAccountConfirmation(application, "0");


    }

    @Transactional
    public DatabaseModel generateSucceed(DatabaseModel Application) {


        AccountInfo accountInfo = new AccountInfo();
        accountInfo = (AccountInfo) this.operator.getTargetObject(Application, accountInfo.newInstanceWithoutArgs());


        accountInfo.setTaaccountid(this.accountConfirmation.getTaaccountid());
        accountInfo.setAccountinfoid(1);
        accountInfo.setTransactionaccountid("1");


        AcctShare acctShare = new AcctShare();
        acctShare = (AcctShare) this.operator.getTargetObject(Application, acctShare.newInstanceWithoutArgs());
        acctShare = (AcctShare) this.operator.getTargetObject(accountInfo, acctShare);
        acctShare.setTotalvolofdistributorinta(BigDecimal.valueOf(0));

        acctShare.setDistributorcode("0");
        acctShare.setTransactioncfmdate("");


        try {
            //        写入 "account_info " 表
            accountInfoDao.insert(accountInfo);
            //        写入 "acct_share" 表
            acctShareDao.insert(acctShare);
            //        写入"account_confirmation"表
            accountConfirmationDao.insert(this.accountConfirmation);

            if (Application.getClass().getSimpleName().equals("File01")) {
                AccountApplication accountApplication = (AccountApplication) Application;
                accountApplication.setRecordStatus("2");
                accountApplicationDao.updateByPrimaryKey(accountApplication);
            }
            return this.accountConfirmation;


        } catch (Exception e) {
            logger.error("insert:" + e);
        }
        return null;

    }

}
