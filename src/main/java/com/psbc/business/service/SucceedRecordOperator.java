package com.psbc.business.service;

import com.psbc.TaMockProjectApplication;
import com.psbc.mapper.*;
import com.psbc.pojo.*;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    public String generateRecord(AccountApplication application, AccountExpectation expectation) {

        AccountConfirmation accountConfirmation = new AccountConfirmation();
        accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());
        if (expectation != null) {
            this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(expectation, accountConfirmation);
        }

        this.getAccountConfirmation(application, "0");

        this.returnCode = expectation.getReturncode();

        return this.returnCode;
    }

    public void getAccountConfirmation(AccountApplication application, String taCode) {
//      taCode应该配置
        TaProperty taProperty = taPropertyDao.selectByPrimaryKey(taCode);
        String accountprefix = taProperty.getAccountPrefix();
        Integer accountindex = taProperty.getAccountIndex();


        this.accountConfirmation.setBusinesscode("1" + application.getBusinessCode().substring(1));

//        TransactionCfmDate: T+1;  无字段（已手动添加） 无明确解释
//        MultiAcctFlag: 0；  为了生成确认文件

//      TaAccountid 随机生成待改进
        this.accountConfirmation.setTaaccountid(accountprefix + accountindex + (int) (Math.random() * (100000000)));
        this.accountConfirmation.setTransactioncfmdate("");
        this.accountConfirmation.setTaserialno(getFullNowDateTime() + (int) (Math.random() * (1000)));
        this.accountConfirmation.setFromtaflag("0");
        this.accountConfirmation.setRegioncode("0001");
        this.accountConfirmation.setReturncode(this.returnCode);


    }

    public void generateRecord(AccountApplication application, String returnCode) {


        AccountConfirmation accountConfirmation = new AccountConfirmation();
        this.accountConfirmation = (AccountConfirmation) this.operator.getTargetObject(application, accountConfirmation.newInstanceWithoutArgs());

        this.returnCode = returnCode;
        this.getAccountConfirmation(application, "0");


    }


    public void generateSucceed(AccountApplication accountApplication) {


        AccountInfo accountInfo = new AccountInfo();
        accountInfo = (AccountInfo) this.operator.getTargetObject(accountApplication, accountInfo.newInstanceWithoutArgs());


        accountInfo.setTaaccountid(this.accountConfirmation.getTaaccountid());
        accountInfo.setAccountinfoid(1);
        accountInfo.setTransactionaccountid("1");


        AcctShare acctShare = new AcctShare();
        acctShare = (AcctShare) this.operator.getTargetObject(accountApplication, acctShare.newInstanceWithoutArgs());
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

        } catch (Exception e) {
            logger.error("insert:" + e);
        }


        System.out.println();
    }

}
