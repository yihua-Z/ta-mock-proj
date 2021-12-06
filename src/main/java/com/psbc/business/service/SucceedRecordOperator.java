package com.psbc.business.service;

import com.psbc.TaMockProjectApplication;
import com.psbc.mapper.*;
import com.psbc.pojo.*;
import com.psbc.pojo.DatabaseModel;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;
import java.math.BigDecimal;

import static com.psbc.business.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.*;

@Data
@Component
public class SucceedRecordOperator {

    private RecordOperator operator = new RecordOperator();
    private AccountInfo accountInfo = new AccountInfo();
    private AcctShare acctShare = new AcctShare();
    private String returnCode;
    private AccountConfirmation accountConfirmation = new AccountConfirmation();
    private TransactionApplication transactionApplication=new TransactionApplication();
    private TransactionConfirmation transactionConfirmation =new TransactionConfirmation();

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

    @Autowired
    TransactionConfirmationDao transactionConfirmationDao;

    @Autowired
    TransactionApplicationDao transactionApplicationDao;
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
        String accountprefix = "";
        Integer accountindex=0;
        try {
            TaProperty taProperty = taPropertyDao.selectByPrimaryKey(taCode);
            accountprefix = taProperty.getAccountPrefix();
            accountindex = taProperty.getAccountIndex();

        } catch (Exception e) {
            logger.error(e);
        }


        if (application.getClass().getSimpleName().equals("AccountApplication")) {
            AccountApplication accountApplication = (AccountApplication) application;
            this.accountConfirmation.setBusinesscode("1" + accountApplication.getBusinesscode().substring(1));
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
//        accountInfo = (AccountInfo) this.operator.getTargetObject(Application, accountInfo.newInstanceWithoutArgs());
        copyFields(Application,accountInfo);

        accountInfo.setAccountinfoid(Integer.valueOf(this.accountConfirmation.getTaaccountid()));
        accountInfo.setTaacountid("EW");
        accountInfo.setTransactionaccountid("1");


        AcctShare acctShare = new AcctShare();
//        copyFields(Application,acctShare);
//        copyFields(accountInfo,acctShare);
        acctShare = (AcctShare) this.operator.getTargetObject(Application, acctShare.newInstanceWithoutArgs());
        acctShare = (AcctShare) this.operator.getTargetObject(accountInfo, acctShare);

        acctShare.setTaaccountid(this.accountConfirmation.getTaaccountid());
        acctShare.setTacode("EW");
        acctShare.setFundcode("EW0002");
        acctShare.setDistributorcode("PSB");
        acctShare.setTotalamountofdistributorinta(BigDecimal.valueOf(0));;
        acctShare.setTotalvolofdistributorinta(BigDecimal.valueOf(0));
        acctShare.setUpdatedate(getNowDate());
        acctShare.setTotalfrozenvol(BigDecimal.valueOf(0));
        acctShare.setTotalfrozenamount(BigDecimal.valueOf(0));

        try {
            //        写入 "account_info " 表
            accountInfoDao.insert(accountInfo);
            //        写入 "acct_share" 表
            acctShareDao.insert(acctShare);
            //        写入"account_confirmation"表
            accountConfirmationDao.insert(this.accountConfirmation);

            if (Application.getClass().getSimpleName().equals("AccountApplication")) {
                AccountApplication accountApplication = (AccountApplication) Application;
                accountApplication.setRecordstatus("2");
                accountApplicationDao.updateByPrimaryKey(accountApplication);
            }
            return this.accountConfirmation;


        } catch (Exception e) {
            logger.error("insert:" + e);
        }
        return null;

    }

    @Transactional
    public DatabaseModel generateTransactionSucceed(DatabaseModel Application) {

        TransactionConfirmation transactionConfirmation = new TransactionConfirmation();
        this.operator.getTargetObject(Application,transactionConfirmation.newInstanceWithoutArgs());


        AcctShare acctShare = new AcctShare();
        acctShare = (AcctShare) this.operator.getTargetObject(Application, acctShare.newInstanceWithoutArgs());
        acctShare = (AcctShare) this.operator.getTargetObject(accountInfo, acctShare);
        acctShare.setTotalvolofdistributorinta(BigDecimal.valueOf(0));

        acctShare.setDistributorcode("0");
        acctShare.setUpdatedate(getNowDate());


        try {
            //        写入 "account_info " 表
            accountInfoDao.insert(accountInfo);
            //        写入 "acct_share" 表
            acctShareDao.insert(acctShare);
            //        写入"transactionConfirmation"表
            transactionConfirmationDao.insert(transactionConfirmation);

            if (Application.getClass().getSimpleName().equals("transactionApplication")) {
                AccountApplication accountApplication = (AccountApplication) Application;
                TransactionApplication transactionApplication =(TransactionApplication) Application;
//                更新transactionApplication部分值
//                transactionApplication.set
                transactionApplicationDao.updateByPrimaryKey(transactionApplication);
            }
            return this.transactionConfirmation;



        } catch (Exception e) {
            logger.error("insert:" + e);
        }
        return null;

    }

    public String generateTransactionRecord(DatabaseModel application, TransactionExpectation expectation) {

        TransactionConfirmation transactionConfirmation =new TransactionConfirmation();
        transactionConfirmation=(TransactionConfirmation)this.operator.getTargetObject(application,transactionConfirmation.newInstanceWithoutArgs());
        if (expectation != null) {
            this.transactionConfirmation=(TransactionConfirmation)this.operator.getTargetObject(expectation,transactionConfirmation) ;
        }
        this.returnCode = expectation.getReturncode();

        return this.returnCode;
    }

    public void generateTransactionRecord(DatabaseModel application, String returnCode) {

        AccountConfirmation accountConfirmation = new AccountConfirmation();
        this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());

        TransactionConfirmation transactionConfirmation =new TransactionConfirmation();
        this.transactionConfirmation=(TransactionConfirmation)this.operator.getTargetObject(application,transactionConfirmation.newInstanceWithoutArgs());
        this.returnCode = returnCode;
//        this.getAccountConfirmation(application, "0");


    }

}
